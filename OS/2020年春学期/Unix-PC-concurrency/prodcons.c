#include<stdio.h>
#include<pthread.h> 
#define BUFSIZE 1000 

struct prodcons {
	int buffer[BUFSIZE]; 
	pthread_mutex_t lock;  //互斥LOCK
	int readpos , writepos; 
	pthread_cond_t notempty; //缓冲区非空条件判断
	pthread_cond_t notfull;  //缓冲区未满条件判断
};

void init(struct prodcons * b){
	pthread_mutex_init(&b->lock,NULL);
	pthread_cond_init(&b->notempty,NULL);
	pthread_cond_init(&b->notfull,NULL);
	b->readpos=0;
	b->writepos=0;
}

void put(struct prodcons* b,int data){
	//如果互斥锁mutex已经被上锁则挂起生产者线程,并返回
	pthread_mutex_lock(&b->lock);
	//等待缓冲区未满
	if((b->writepos + 1) % BUFSIZE == b->readpos){
		//缓冲区满,生产者将被挂起,直至重新被唤醒
		pthread_cond_wait(&b->notfull, &b->lock) ;
	}
	//写数据,并移动指针
	b->buffer[b->writepos]=data;
	b->writepos++;
	if(b->writepos >= BUFSIZE)
		b->writepos=0;
	//设置缓冲区非空的条件变量
	pthread_cond_signal(&b->notempty);
	pthread_mutex_unlock(&b->lock);
}

int get(struct prodcons *b){
	int data;
	pthread_mutex_lock(&b->lock);
	if(b->writepos == b->readpos){
		//等待缓冲区非空
		pthread_cond_wait(&b->notempty, &b->lock);
	}
	//读数据,移动读指针
	data = b->buffer[b->readpos];
	b->readpos++;
	if(b->readpos >= BUFSIZE)
		b->readpos=0;
	//设置缓冲区未满的条件变量
	pthread_cond_signal(&b->notfull);
	pthread_mutex_unlock(&b->lock);
	return data;
}

#define OVER (-1)

struct prodcons buffer;
void *producer(void *data){
	int n;
	for(n = 0; n <5000; n++){
	  put(&buffer, n);
          printf("write Number: %d \n", n) ;
 //if (n %100 == 0) 
  //	sleep(1);
	}
	put(&buffer, OVER);
	return NULL;
}
void *consumer(void * data){
	int d;
	while(1){
	  d = get(&buffer);
	  if(d == OVER)
	  	break;
	  printf("Read Number: \
            %d\n", d);
	}
	return NULL;
}

int main(void){
	pthread_t th_a, th_b;
	void *retval;
	init(&buffer);
	pthread_create(&th_a, NULL, producer, 0);
	pthread_create(&th_b, NULL, consumer, 0);
	pthread_join(th_a, &retval);
	pthread_join(th_b, &retval);
	return 0;
}


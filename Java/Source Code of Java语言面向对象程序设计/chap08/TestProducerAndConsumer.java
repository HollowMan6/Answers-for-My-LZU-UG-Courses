public class TestProducerAndConsumer{
  public static void main(String[] args){
     ProductMsgQueue products=new ProductMsgQueue();
     Producer producer=new Producer(products); //产生一个生产者对象
     Consumer consumer=new Consumer(products); //产生一个消费者对象
     producer.start();   //生产者线程进入运行状态
     consumer.start();  //消费者线程进入运行状态
  }
}
class ProductMsgQueue{
  static final int MAX_QUEUE_LENGTH=5; //消息队列最大长度
  static int i=0;
  String[] msgArray;  //队列中的消息
  int queueLength; //当前队列长度
  public ProductMsgQueue(){  //设置队列最大长度和起始长度
    msgArray=new String[MAX_QUEUE_LENGTH];
    queueLength=0;
  }
  public synchronized void putMessage() { //向产品消息队列中存放消息
    try{
      while(queueLength==MAX_QUEUE_LENGTH)
      wait(); //如果队列长度达到最大，等待消费者取走产品消息，线程进入睡眠状态
    }catch(InterruptedException e){
       System.out.println("InterruptedException in putMessage: "+e);
    }
    msgArray[queueLength++]="Message:" + i;
    System.out.println("Message "+i+" is put into the queue by the producer!");
    ++i;
    notifyAll();  //唤醒所有线程
  }
  public synchronized String getMessage() { //获取产品消息队列中的产品消息
    try{
       while(queueLength==0)
       wait();  //如果队列长度为0，则等待生产者存入产品消息，线程进入睡眠状态
    }catch(InterruptedException e){
       System.out.println("InterruptedException in getMessage: "+e);
    }
    String msg=msgArray[--queueLength];
    System.out.println(msg+" is gotten from the queque by the consumer!");
    notifyAll();  //唤醒所有线程
    return msg;      
  }
}
class Producer extends Thread{
  ProductMsgQueue productMsg;
  public Producer(ProductMsgQueue productMsg){
    this.productMsg=productMsg;
  }
  public void run(){
    while(true){
      productMsg.putMessage(); //向产品消息队列中添加产品消息
      try{
        sleep(1000); //休眠1000毫秒
      }catch(InterruptedException e){
        System.out.println("InterruptedException in revoking putMessage: "+e);
      }      
    }
  }
}
class Consumer extends Thread{
  ProductMsgQueue productMsg;
  public Consumer(ProductMsgQueue productMsg){
      this.productMsg=productMsg;
  }
  public void run(){
     while(true){
       productMsg.getMessage();
       try{
         sleep(3000); //休息2000毫秒
       }catch(InterruptedException e){
         System.out.println("InterruptedException in revoking getMessage: "+e);
       }
     }
  }
}
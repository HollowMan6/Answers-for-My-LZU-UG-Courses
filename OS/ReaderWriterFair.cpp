/* By HollowMan6 from Lanzhou University(兰州大学) using Pseudocode */
/* program ReaderWriterFair */
int readcount; 
semaphore x = 1, y = 1 wsem = 1; 
void reader()
{
    while (true)
    {
        semWait(y);
            semWait(x);
                if (readcount == 0) semWait(wsem);
                readcount++;
            semSignal(x);
        semSignal(y);
        READUNIT();
        semWait(x);
            readcount--;
            if (readcount == 0) semSignal(wsem);
        semSignal(x);
    }
    
}

void writer()
{
    semWait(y);
        semWait(wsem);
        WRITEUNIT();
        semSignal(wsem);
    semSignal(y);
}

void main()
{
    readcount = 0;
    parbegin(reader, writer);
}
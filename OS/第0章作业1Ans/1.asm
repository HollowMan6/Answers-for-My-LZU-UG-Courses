DATA SEGMENT
    ;分配50字节，用$填充方便输出，存放输入的姓名
    DB 50 DUP('$')
    ;分配10字节，用$填充方便输出，存放输入的班级
    CLASS DB 10 DUP('$')
    ;分配50字节，用$填充方便输出，存放输入的专业
    MAJOR DB 50 DUP('$')
    ;存储换行符
    MES0 DB 0DH,0AH,'$'
    ;存储提示信息
    MES1 DB 'Come on, Wuhan! I am ','$'
    MES2 DB ', a student of Lanzhou University at ','$'
    MES3 DB ' class and majoring in $'
    MES5 DB 'Please input your name:','$'
    MES6 DB 'Please input your class:','$'
    MES7 DB 'Please input your major:','$'
DATA ENDS

;问题2. 除中断功能所设计的寄存器外，此程序涉及到AX,DS,DX寄存器

;问题3. 当调用21号中断时由程序调用DOS操作系统实现，
;当需输出字符时（AH=9），系统将内存中的信息传送到显存中，并显示到屏幕上，当需输入字符时（AH=0AH），
;系统将键盘输入的字符加载到显存，显示到屏幕上，并将其写入指定内存。当程序退出（AH=4CH）时，释放程序占用的内存和资源。

CODE SEGMENT
ASSUME CS:CODE,DS:DATA
    START:
    ;初始化DS的寄存器值 AX和DS变为DATA的内存地址
    MOV AX,DATA
    MOV DS,AX
    ;让DX指向MES5的起始地址 DX变为0C5H
    MOV DX,OFFSET MES5
    ;调用AH=9时的21号中断功能，即将存储的MES5字符串输出，AH变为9
    MOV AH,9
    INT 21H
    ;DX变为0
    MOV DX,0
    ;调用AH=0AH时的21号中断功能，即将输入的姓名字符串存储起来，AH变为0AH
    MOV AH,0AH
    INT 21H
    ;让DX指向MES0的起始地址 DX变为6EH
    MOV DX,OFFSET MES0
    ;调用AH=9时的21号中断功能，即将存储的换行字符串输出，AH变为9
    MOV AH,9
    INT 21H
    ;让DX指向MES6的起始地址 DX变为0DDH
    MOV DX,OFFSET MES6
    ;调用AH=9时的21号中断功能，即将存储的MES6字符串输出
    INT 21H
    ;让DX指向CLASS的起始地址 DX变为32H
    MOV DX,OFFSET CLASS
    ;调用AH=0AH时的21号中断功能，即将输入的班级字符串存储起来，AH变为0AH
    MOV AH,0AH
    INT 21H
    ;让DX指向MES0的起始地址 DX变为6EH
    MOV DX,OFFSET MES0
    ;调用AH=9时的21号中断功能，即将存储的换行字符串输出，AH变为9
    MOV AH,9
    INT 21H
    ;让DX指向MES7的起始地址 DX变为F6H
    MOV DX,OFFSET MES7
    ;调用AH=9时的21号中断功能，即将存储的MES7字符串输出
    INT 21H
    ;让DX指向MAJOR的起始地址 DX变为3CH
    MOV DX,OFFSET MAJOR
    ;调用AH=0AH时的21号中断功能，即将输入的MAJOR字符串存储起来，AH变为0AH
    MOV AH,0AH
    INT 21H
    ;让DX指向MES0的起始地址 DX变为6EH
    MOV DX,OFFSET MES0
    ;调用两次AH=9时的21号中断功能，即将存储的换行字符串输出，AH变为9
    MOV AH,9
    INT 21H
    INT 21H
    ;让DX指向MES1的起始地址 DX变为71H
    MOV DX,OFFSET MES1
    ;调用AH=9时的21号中断功能，即将存储的MES1字符串输出
    INT 21H
    ;DX变为0
    MOV DX,0
    ;调用AH=9时的21号中断功能，即将存储的姓名字符串输出
    INT 21H
    ;让DX指向MES0的起始地址 DX变为6EH
    MOV DX,OFFSET MES0
    ;调用AH=9时的21号中断功能，即将存储的换行字符串输出
    INT 21H
    ;让DX指向MES2的起始地址 DX变为87H
    MOV DX,OFFSET MES2
    ;调用AH=9时的21号中断功能，即将存储的MES2字符串输出
    INT 21H
    ;让DX指向CLASS的起始地址 DX变为32H
    MOV DX,OFFSET CLASS
    ;调用AH=9时的21号中断功能，即将存储的班级字符串输出
    INT 21H
    ;让DX指向MES0的起始地址 DX变为6EH
    MOV DX,OFFSET MES0
    ;调用AH=9时的21号中断功能，即将存储换行的字符串输出
    INT 21H
    ;让DX指向MES3的起始地址 DX变为ADH
    MOV DX,OFFSET MES3
    ;调用AH=9时的21号中断功能，即将存储的MES3字符串输出
    INT 21H
    ;让DX指向MAJOR的起始地址 DX变为3CH
    MOV DX,OFFSET MAJOR
    ;调用AH=9时的21号中断功能，即将存储的专业字符串输出
    INT 21H
    ;调用AH=4CH时的21号中断功能，即程序退出，并进行占用资源清理
    MOV AX,4C00H
    INT 21H
CODE ENDS
END START
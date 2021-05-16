STACK SEGMENT
    DB 200 DUP (0)
STACK ENDS
CODE SEGMENT
    ASSUME CS:CODE, SS:STACK
BEGIN:
    MOV AH, 1
    INT 21H
    MOV BL, AL      ;从键盘输入第一个数字，并转存到寄存器BL中
    MOV AH, 2
    MOV DL, 0DH
    INT 21H         ;输出回车
    MOV AH, 2
    MOV DL, 0AH
    INT 21H         ;输出换行
    MOV AH, 1
    INT 21H         ;从键盘输入第二个数字，保存在寄存器AL中
    SUB AL, 30H
    SUB BL, 30H     ;将读入的ASCII码转换成数值
    MUL BL          ;计算AL和BL的乘积，保存在AX中
    MOV BL, 10
    DIV BL          ;十位数字（商）保存在AL中，个位数字（余数）保存在AH中
    ADD AX, 3030H   ;个位十位均加上30H，转换成相应的ASCII码
    MOV BX, AX
    MOV AH, 2
    MOV DL, 0DH
    INT 21H         ;输出回车
    MOV AH, 2
    MOV DL, 0AH
    INT 21H         ;输出换行
    CMP BL,30H
    JE onesout
tensout:
    MOV DL, BL
    MOV AH, 2
    INT 21H         ;输出十位数字
onesout:
    MOV DL, BH
    INT 21H         ;输出个位数字
    MOV AH, 4CH
    INT 21H         ;程序结束，将控制权交DOS
CODE ENDS
    END BEGIN

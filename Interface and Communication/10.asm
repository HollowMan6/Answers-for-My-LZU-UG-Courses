DATA SEGMENT
TS1 DB 'CONVERT',0DH,0AH,'$'
TS2 DB 'STOP?',0DH,0AH,'$'
DATA ENDS
STACKS SEGMENT
STA DB 20 DUP(?)
TOP EQU LENGTH STA
STACKS ENDS
CODE SEGMENT
ASSUME CS:CODE,DS:DATA,SS:STACKS
BEGIN:MOV AX,DATA
    MOV DS,AX
    MOV AX,STACKS
    MOV SS,AX
    MOV SP,TOP
ZCD:LEA DX,TS1
    MOV AH,9
    INT 21H
    MOV AH,8        ;���������ַ����޻��ԣ���AL=�����ַ�
    INT 21H
    MOV AH,AL
    MOV DX,290H
BBB:MOV CX,2000H
BB: OUT DX,AL
    INC DX
    OUT DX,AL
    DEC DX
    LOOP BB
    MOV AH,1
    INT 21H
    CMP AL,'q'
    JZ EXIT
    JNZ ZCD
EXIT:MOV AH,4CH
    INT 21H
    CODE ENDS
    END BEGIN
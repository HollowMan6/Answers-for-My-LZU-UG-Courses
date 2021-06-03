DATA SEGMENT
TS1 DB 'A/D convertion begins.',0DH, 0AH,'$'
TS2 DB 0DH,0AH,'Convert finished.',0DH,0AH,'$'
BUF DW 300 DUP(?)
DATA ENDS
STACK SEGMENT
STA DW 20 DUP(?)
TOP EQU LENGTH STA
STACK ENDS
CODE SEGMENT
ASSUME CS:CODE,DS:DATA,SS:STACK
START:  MOV AX,DATA
        MOV DS,AX
        MOV AX,STACK
        MOV SS,AX
        MOV SP,TOP
        LEA DX,TS1
        MOV AH,9
        INT 21H
        MOV CX,50 ;设置采样次数
CONV:   MOV DX,298H ;选择模拟信号输入端
        MOV AL,0
        OUT DX,AL
        CALL ADINT
        MOV AX,SI
        LEA DI,BUF
        MOV [DI],AL
        AND AL,0F0H
        PUSH CX
        MOV CL,4
        SHR AL,CL
        POP CX
        ADD AL,30H ;转换为ASCII码
        CMP AL,39H
        JBE DISOL
        ADD AL,07H
DISOL:  MOV DL,AL
        MOV AH,2
        INT 21H
        MOV AL,[DI]
        INC DI
        AND AL,0FH
        ADD AL,30H
        CMP AL,39H
        JBE DISPH
        ADD AL,07H
DISPH:  MOV DL,AL ;转换为ASCII码并显示低位数据
        MOV AH,2
        INT 21H
        MOV DL,20H
        MOV AH,2
        INT 21H
        INT 21H
        LOOP CONV
        LEA DX,TS2
        MOV AH,9
        INT 21H
        MOV AH,4CH ;返回DOS
        INT 21H
        ADINT PROC NEAR
        PUSH AX
        PUSH BX
        PUSH CX
        MOV CX,0FFFFH
DELAY:  MOV BX,2FH
DELAY1: DEC BX
        CMP BX,0
        JNZ DELAY1
        LOOP DELAY
        MOV DX,298H
        IN AL,DX
        MOV SI,AX
        POP CX
        POP BX
        POP AX
        RET
ADINT ENDP
CODE ENDS
END START
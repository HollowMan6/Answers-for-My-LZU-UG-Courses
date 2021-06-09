DATA SEGMENT
DB 06H,5BH,4FH,66H,6DH,7DH,07H,7FH,6FH
DATA ENDS
STACK SEGMENT
STA DB 20 DUP(?)
TOP EQU LENGTH STA
STACK ENDS
CODE SEGMENT
ASSUME CS:CODE,DS:DATA
BEGIN:  MOV AX,DATA
        MOV DS,AX
        MOV AX,STACK
        MOV SS,AX
        MOV SP,TOP
        MOV DX,28BH
        MOV AL,80H
        OUT DX,AL
SZ:     MOV CX,9
        MOV BX,8
XS:     MOV AL,[BX]
        MOV DX,28AH
        OUT DX,AL
        CALL DELAY
        DEC BX
        LOOP XS
        JMP XZ
DELAY:  PUSH CX
        MOV CX,0FFFH
FF:     MOV SI,05FFH
ZJ:     DEC SI
        JNZ ZJ
        LOOP FF
        POP CX
        RET
XZ:     MOV AL,'R'
S:        MOV DX,290H
BBB:    MOV CX,10H
BB:     OUT DX,AL
        INC DX
        OUT DX,AL
        DEC DX
        LOOP BB
        CMP AL,'0'
        JZ  EXIT
        CALL DELAY1
        MOV AL,'0'
        JMP S
DELAY1: PUSH CX
        MOV CX,0FFFFH
FF1:    MOV SI,05FFH
ZJ1:    DEC SI
        JNZ ZJ1
        LOOP FF1
        POP CX
        RET
        
EXIT:   MOV AH,4CH
        INT 21H
        CODE ENDS
        END BEGIN
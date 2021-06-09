DATA SEGMENT
DB 00H,3FH,06H,5BH,4FH,66H,6DH,7DH,07H,7FH,6FH,77H,7CH,39H,5EH,79H,71H,80H,0FFH
DATA ENDS
STACKS SEGMENT
STA DB 20 DUP(?)
TOP EQU LENGTH STA
STACKS ENDS
CODE SEGMENT
ASSUME CS:CODE,DS:DATA
BEGIN:  MOV AX,DATA
        MOV DS,AX
        MOV AX,STACKS
        MOV SS,AX
        MOV SP,TOP
        MOV DX,28BH
        MOV AL,80H
        OUT DX,AL
INPUT:  MOV AH,1
        INT 21H
        CMP AL,'q'
        JZ STOP
        CMP AL,'E'
        JZ EXIT
        MOV AH,0
        SUB AL,48
        CMP AL,9
        JLE SZ
ZM:     SUB AL,39
SZ:     INC AX
        MOV BX,AX
        INC AX
        MOV CX,AX
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
XZ:     MOV AL,'S'
S:      MOV DX,290H
BBB:    MOV CX,11H
BB:     OUT DX,AL
        INC DX
        OUT DX,AL
        DEC DX
        LOOP BB
        JMP INPUT
STOP:   MOV AL,0
        JMP S
EXIT:   MOV AH,4CH
        INT 21H
        CODE ENDS
        END BEGIN
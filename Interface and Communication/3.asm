DATA SEGMENT
TS DB 'PRESS 1,LAMB ON ONCE,PRESS 2,LAMB ON TWICE,PRESS 3,LAMB ON THIRD,PRESS Q QUIT',0DH,0AH,'$'
TS1 DB 'OVER',0AH,0DH,'$'
DATA ENDS
CODE SEGMENT
ASSUME CS:CODE,DS:DATA
START:  MOV AX,DATA
        MOV DS,AX
        LEA DX,TS
        MOV AH,9
        INT 21H
ZL:     MOV AH,01H
        INT 21H
        CMP AL,31H
        JZ L
        CMP AL,32H
        JZ LEC
        CMP AL,33H
        JZ LS
        CMP AL,71H
        JZ JIESHU
        JMP ZL
L:      MOV DX,283H
        MOV AL,30H
        OUT DX,AL
        MOV DX,280H
        MOV AL,0AAH
        OUT DX,AL
        OUT DX,AL
        JMP ZL
LEC:    MOV DX,283H
        MOV AL,70H
        OUT DX,AL
        MOV DX,281H
        MOV AL,0AAH
        OUT DX,AL
        OUT DX,AL
        MOV CX,0FFFFH
WAIT3:  MOV BX,2000H
WAIT4:  DEC BX
        JNZ WAIT4
        LOOP WAIT3
        MOV DX,281H
        MOV AL,0AAH
        OUT DX,AL
        OUT DX,AL
        JMP ZL
LS:     MOV DX,283H
        MOV AL,0B0H
        OUT DX,AL
        MOV DX,282H
        MOV AL,0AAH
        OUT DX,AL
        OUT DX,AL
        MOV CX,0FFFFH
WAIT1:  MOV BX,2000H
WAIT2:  DEC BX
        JNZ WAIT2
        LOOP WAIT1
        MOV DX,282H
        MOV AL,0AAH
        OUT DX,AL
        OUT DX,AL
        MOV CX,0FFFFH
WAIT11: MOV BX,2000H
WAIT22: DEC BX
        JNZ WAIT22
        LOOP WAIT11
        MOV DX,282H
        MOV AL,0AAH
        OUT DX,AL
        OUT DX,AL
        JMP ZL
JIESHU: LEA DX,TS1
        MOV AH,9
        INT 21H
        MOV AH,4CH
        INT 21H
CODE ENDS
END START
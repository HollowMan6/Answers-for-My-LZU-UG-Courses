DATA SEGMENT
LED DB 3FH,06H,5BH,4FH,66H,6DH,7DH,07H,7FH,6FH,77H,7CH,39H,5EH,79H,71H,80H,0FFH
MESS DB 0DH,0AH,'PLEASE INPUT m OR n',0DH,0AH,'$'
DATA ENDS
CODE SEGMENT 
ASSUME CS:CODE,DS:DATA
START:
MOV AX,DATA
MOV DS,AX
MOV DX,28BH
MOV AX,89H
OUT DX,AL
MOV BX,OFFSET LED
CMPP:
LEA DX,MESS
MOV AH,09H
INT 21H
MOV AH,1
INT 21H
CMP AL,6EH
JE SSS
CMP AL,6DH
JNE EEE
MOV AH,1
INT 21H
SUB AL,48
CMP AL,9
JMP LETTER
XLAT
MOV DX,288H
OUT DX,AL
MOV DL,7
MOV AH,2
INT 21H
JMP WAIK
LETTER:
SUB AL,39
XLAT
MOV DX,288H
OUT DX,AL
MOV DL,7
MOV AH,2
INT 21H
JMP WAIK
SSS:
MOV DX,28AH
IN AL,DX
OR AL,AL
JE SSS
MOV CL,0FFH
RR:
SHR AL,1
INC CL
JNC RR
MOV AL,CL
XLAT
MOV DX,288H
OUT DX,AL
MOV DL,7
MOV AH,2
INT 21H
WAIK:
MOV AH,1
INT 21H
CMP AL,20H
JNE EEE
MOV AL,0
MOV DX,288H
OUT DX,AL
JMP CMPP
EEE:
MOV AH,4CH
INT 21H
CODE ENDS
END START
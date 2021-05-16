code segment
assume cs:code
G1: 
MOV AH,1
INT 21H
CMP AL,'$'
JZ EXIT
MOV DL,AL
MOV AH,2
INT 21H
JMP G1
EXIT:
MOV AH,4CH
INT 21H
code ends
end G1
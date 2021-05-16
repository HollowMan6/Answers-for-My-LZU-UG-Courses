data segment
BUF DB 'Hello',13,10,'END$'
data ends
stack segment
DB 100 DUP(0)
stack ends
code segment
assume cs:code,ds:data,ss:stack
begin:
MOV AX,DATA
MOV DS,AX
LEA BX,BUF
la:
MOV DL,[BX]
CMP DL,'$'
JZ exit
CMP DL,'a'
JB k
CMP DL,'z'
JA k
SUB DL,20H
k:
MOV AH,2
INT 21H
INC BX
JMP la
exit:
MOV AH,4CH
INT 21H
code ends
end begin
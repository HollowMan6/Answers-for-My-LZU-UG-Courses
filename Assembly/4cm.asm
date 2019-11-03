data segment
msg db 'Please input a string:',0AH,0DH,'$'
data ends
code segment
assume cs:code,ds:data

begin:
mov ax,data
mov ds,ax
mov dx,offset msg
mov ah,9
int 21h
mov bx,0
mov es,bx
mov bx,200h

input:
mov ah,1
int 21h
cmp al,'$'
jz space
and al,11011111B
mov es:[bx],al
inc bx
jmp input

space:
mov cx,bx
mov ah,2
mov al,0AH
mov dl,al
int 21h
mov ah,2
mov al,0DH
mov dl,al
int 21h
mov bx,200h
jmp output

output:
mov ah,2
cmp bx,cx
jz exit
mov al,es:[bx]
inc bx
mov dl,al
int 21h
jmp output

exit:
mov ah,4ch
int 21h
code ends
end begin
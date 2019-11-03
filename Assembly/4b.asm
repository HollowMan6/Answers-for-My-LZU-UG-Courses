assume cs:code
code segment
mov bx,200h
mov ax,0
mov ds,ax
mov cx,64
s:mov [bx],al
inc bx
inc ax
loop s
mov ax,4c00h
int 21h
code ends
end

assume cs:code
code segment
mov ax,cs
mov ds,ax
mov ax,0020h
mov es,ax
mov bx,0
mov cx,offset a
s:mov al,[bx]
mov es:[bx],al
inc bx
loop s
a:mov ax,4c00h
int 21h
code ends
end

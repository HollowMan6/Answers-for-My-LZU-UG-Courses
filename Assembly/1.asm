assume cs:codesg
codesg segment
mov ax,2000h
mov ss,ax
mov sp,10h
add sp,4
push ax
push bx
pop ax
pop bx
mov ax,4c00h
int 21h
codesg ends
end
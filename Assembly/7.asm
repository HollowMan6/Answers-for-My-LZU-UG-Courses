codes segment
assume cs:codes
mov ah,6
mov al,0
mov ch,0
mov cl,0
mov dh,24
mov dl,79
mov bh,7
int 10h
mov ah,6
mov al,1
mov ch,8
mov cl,30
mov dh,16
mov dl,50
mov bh,206
int 10h
Poscurse:
mov ah,2
mov dh,16
mov dl,30
mov bh,0
int 10h
mov cx,20
Getchar:
mov ah,1
int 21h
cmp al,'Q'
jz exit
loop Getchar
mov ah,6
mov al,1
mov ch,8
mov cl,30
mov dh,16
mov dl,50
mov bh,206
int 10h
jmp Poscurse
Exit:
mov ah,4ch
int 21h
codes ends
end
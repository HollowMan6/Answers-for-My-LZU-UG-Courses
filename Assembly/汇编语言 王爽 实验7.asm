data segment
    db '1975','1976','1977','1978','1979','1980','1981','1982','1983'
    db '1984','1985','1986','1987','1988','1989','1990','1991','1992'
    db '1993','1994','1995'
    ;以上表示21年的21个字符串

    dd 16,22,382,1356,2390,8000,16000,24486,50065,97479,140417,197514
    dd 345980,590827,803530,1183000,1843000,2759000,3753000,4649000,5937000
    ;以上是表示21年公司总收入的21个dword型数据

    dw 3,7,9,13,28,38,130,220,476,778,1001,1442,2258,2793,4037,5635,8226
    dw 11542,14430,15257,17800
    ;以上是表示21年公司雇员人数的21个word型数据
data ends

table segment
    db 21 dup ('year summ ne ??')
table ends

codesg segment
assume cs:codesg,ds:data
start:
mov ax,table
mov ds,ax
mov ax,data
mov es,ax
mov bx,0
mov di,0
mov si,0
mov cx,21
write:
mov dx,es:[si]
mov [bx],dx
mov dx,es:[si+2]
mov [bx+2],dx
mov dx,es:[si+84]
mov [bx+5],dx
mov dx,es:[si+86]
mov [bx+7],dx
mov dx,es:[di+168]
mov [bx+10],dx
mov ax,es:[si+84]
mov dx,es:[si+86]
div word ptr es:[di+168]
mov [bx+13],ax
add bx,15
add di,2
add si,4
loop write
mov ax,4c00h
int 21h
codesg ends
end start
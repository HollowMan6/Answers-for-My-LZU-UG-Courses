stack segment stack
    db 64 dup(?)
stack ends
code segment
    assume cs: code, ss: stack
start:
    mov dl, 0dh
    mov ah, 2
    int 21h
    mov dl, 0ah
    int 21h             ;输出换行
    mov bl, 0
next:
    mov al, bl
    mov cl, 4
    shr al, cl          ;高四位移动到低四位，高四位置0
    or al, 30h          ;相当于al值增加30h，转换为ASCII码
    mov dl, al
    mov ah, 2
    int 21h             ;输出第一位数字
    mov dl, bl
    and dl, 0fh         ;高四位置零
    or dl, 30h          ;转换为ASCII码
    mov ah, 2
    int 21h             ;输出第二位数字
    mov dl, ' '
    int 21h             ;输出空格
    mov al, bl
    inc al              ;al的值自增
    daa                 ;对al进行加法校正
    cmp al, 30h
    jnc finish          ;(al)≥30时跳到finish
    mov bl, al          ;bl高、低四位分别设置为要输出的下一个数的十位和个位
    mov cx, 0ffffh
delay:
    loop delay          ;延时程序，去抖动
    jmp next            ;继续输出下一个数
finish:
    mov ah, 4ch
    int 21h             ;程序返回
code ends
    end start

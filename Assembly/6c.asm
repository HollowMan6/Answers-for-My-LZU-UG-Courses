stack segment stack
    dw 64 dup (?)
stack ends
code segment
    assume cs: code, ss: stack
start:
    mov ax, stack
    mov ss, ax
    mov sp, 64          ;设置栈
    mov cx, 001ah       ;(cx)=26
    mov bl, 41h
    mov ah, 02h         ;中断21h的功能号2用于输出寄存器dl中的单个字符
a1: mov dl, bl
    int 21h             ;输出字母
    inc bl
    push cx             ;保存寄存器cx的值
    mov cx, 0fffh
a2: loop a2             ;延时程序，去抖动
    pop cx              ;恢复寄存器cx的值
    dec cx
    jnz a1              ;cx的值不为0时跳转到a1
    mov ax, 4c00h
    int 21h             ;返回DOS
code ends
    end start

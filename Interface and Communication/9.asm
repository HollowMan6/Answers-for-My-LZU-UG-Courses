;************************;
;*8251串行通讯(自发自收)*;
;************************;
data segment
 io8253a    equ 280h
 io8253b    equ 283h
 io8251a    equ 2b8h
 io8251b    equ 2b9h
 mes1 db 'you can play a key on the keybord!',0dh,0ah,24h
 mes2 dd  mes1
data ends
code segment
   assume cs:code,ds:data
start:      mov ax,data
      mov ds,ax
      mov dx,io8253b       ;设置8253计数器0工作方式
      mov al,16h
      out dx,al
      mov dx,io8253a
      mov al,52         ;给8253计数器0送初值
      out dx,al
      mov dx,io8251b       ;初始化8251
      xor al,al
      mov cx,03         ;向8251控制端口送3个0
 delay:     call out1
      loop delay
      mov al,40h        ;向8251控制端口送40H,使其复位
      call out1
      mov al,4eh        ;设置为1个停止位,8个数据位,波特率因子为16
      call out1
      mov al,27h        ;向8251送控制字允许其发送和接收
      call out1
      lds dx,mes2       ;显示提示信息
      mov ah,09
      int 21h
waiti:   mov dx,io8251b
     in al,dx
     test al,01        ;发送是否准备好
     jz waiti
     mov ah,01         ;是,从键盘上读一字符
     int 21h
     cmp al,27         ;若为ESC,结束
     jz exit
     mov dx,io8251a
     inc al
     out dx,al         ;发送
     mov cx,40h
s51:       loop s51          ;延时
next:      mov dx,io8251b
     in al,dx
     test al,02        ;检查接收是否准备好
     jz next           ;没有,等待
     mov dx,io8251a
     in al,dx          ;准备好,接收
     mov dl,al
     mov ah,02         ;将接收到的字符显示在屏幕上
     int 21h
     jmp waiti
exit:      mov ah,4ch        ;退出
     int 21h
out1 proc near       ;向外发送一字节的子程序
      out  dx,al
      push cx
      mov  cx,40h
gg:       loop gg           ;延时
      pop  cx
      ret
out1 endp
code ends
end start

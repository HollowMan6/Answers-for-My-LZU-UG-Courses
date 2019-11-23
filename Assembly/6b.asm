ASSUME CS: CODE
CODE segment
START:
    MOV AH, 1
    INT 21H
    CMP AL, 0DH         ;判断输入字符是否为回车符
    JZ tc               ;若为回车符直接退出
    CMP AL, '0'
    JB NEXT             ;如果比'0'小则为其它字符,重新开始执行
    CMP AL, '9'
    JA a                ;如果比'9'大跳到a继续判断是否为字母
    MOV DL, AL
    MOV AH, 2
    INT 21H             ;显示输入的数字
    MOV DL, ' '
    INT 21H             ;显示空格
    JMP START           ;重新开始执行
a:  CMP AL, 41H
    JB NEXT             ;如果比'A'小重新开始执行
    CMP AL, 5AH
    JA b                ;如果比'Z'大跳到b继续判断是否为小写字母
c:  MOV DL, 'C'
    MOV AH, 2
    INT 21H             ;输出'C'
    MOV DL, ' '
    INT 21H             ;输出空格
NEXT:
    JMP START
b:  CMP AL, 61H
    JB NEXT             ;如果比'a'小重新开始执行
    CMP AL, 7AH
    JA NEXT             ;如果比'z'大重新开始执行
    JMP c               ;输入内容是字母，跳到c继续执行
tc: MOV AH, 4CH
    INT 21H             ;程序返回
CODE ENDS
    END START

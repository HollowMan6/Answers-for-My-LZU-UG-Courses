keywords = ["if", "else", "while", "break", "continue", "for", "double", "int", "float", "long",
            "short", "switch", "case", "return", "void", "struct"]
operators = ["+", "++", "-", "--", "+=", "-=", "*", "*=", "%", "%=", "->", "|", "||", "|=",
            "/", "/=", ">", "<", ">=", "<=", "=", "==", "!=", "!",]
symbols = ["{", "}", "[", "]", "(", ")", "~", ",", ";", ".", "?", ":"]
# Types=["Identifier","Integer","Symbol","Keyword","Operator","EOF"]


def NextToken(code, line, pointer):
    lexem = ""
    # Code is empty or pointer exceeds the line size
    if code == []:
        return (None, "EOF"), line, pointer
    while True:
        if line >= len(code):
            return (None, "EOF"), line, pointer
        if pointer >= len(code[line]):
            pointer = 0
            line += 1
        else:
            break
    # Consuming beginning tab and whitespace and check comments
    while True:
        while code[line][pointer] == ' ' or code[line][pointer] == '\t':
            pointer += 1
            # make sure haven't reached the end of line or file
            while True:
                if line >= len(code):
                    return (None, "EOF"), line, pointer
                if pointer >= len(code[line]):
                    pointer = 0
                    line += 1
                else:
                    break
        while pointer+1 < len(code[line]) and code[line][pointer] == '/':
            if code[line][pointer+1] == '/':
                line += 1
                pointer = 0
                if line >= len(code):
                    return (None, "EOF"), line, pointer
            elif code[line][pointer+1] == '*':
                pointer += 2
                # make sure haven't reached the end of line or file
                while True:
                    if pointer+1 < len(code[line]) and code[line][pointer] == '*':
                        if code[line][pointer+1] == '/':
                            pointer += 2
                            break
                    pointer += 1
                    # make sure haven't reached the end of line or file
                    while True:
                        if line >= len(code):
                            raise Exception("Lexical", line+1,
                                            "EOF", 'Comments end without */')
                        if pointer >= len(code[line]):
                            pointer = 0
                            line += 1
                        else:
                            break
            # it's divide symbol
            else:
                pointer+=1
                return ("/", "Symbol"), line, pointer
            # make sure haven't reached the end of line or file
            while True:
                if line >= len(code):
                    return (None, "EOF"), line, pointer
                if pointer >= len(code[line]):
                    pointer = 0
                    line += 1
                else:
                    break
        # make sure haven't reached the end of line or file
        while True:
            if line >= len(code):
                return (None, "EOF"), line, pointer
            if pointer >= len(code[line]):
                pointer = 0
                line += 1
            else:
                break
        # To ensure that no remained tab and whitespace and comments
        if not(code[line][pointer] == ' ' or code[line][pointer] == '\t' or code[line][pointer] == '\\'):
            break
    # begin getting a Token
    while pointer < len(code[line]) and code[line][pointer] != ' ':
        # raise error if there exists special symbols
        if not (code[line][pointer].isalnum() or code[line][pointer] == '_' or code[line][pointer] in operators or code[line][pointer] in symbols):
            raise Exception("Lexical", line+1,
                            code[line][pointer], 'unrecognised symbol')
        lexem += code[line][pointer]
        # To identify symbols
        if code[line][pointer] in operators:
            pointer += 1
            return (lexem, "Operator"), line, pointer
        elif code[line][pointer] in symbols:
            pointer += 1
            return (lexem, "Symbol"), line, pointer
        # To cut symbols with words
        elif pointer+1 < len(code[line]) and not (code[line][pointer+1].isalnum() or code[line][pointer+1] == '_'):
            pointer += 1
            break
        pointer += 1
    # Reconize the type of lexem
    if lexem in keywords:
        return (lexem, "Keyword"), line, pointer
    elif lexem.isnumeric():
        return (int(lexem), "Integer"), line, pointer
    elif (lexem[0].isalpha() or lexem[0] == "_") and len(lexem[0])<=15:
        return (lexem, "Identifier"), line, pointer
    # Error when don't match any type of lexem
    else:
        raise Exception("Lexical", line+1,
                        code[line][pointer-1], 'wrong identifier')

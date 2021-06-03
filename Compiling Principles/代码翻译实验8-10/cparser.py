#!/usr/bin/python3
# By Songlin Jiang, 320180901941


class CParser:

    token = None
    line = 0
    generated = ""

    def __init__(self, token):
        self.token = token

    def block(self):
        nextToken=self.token.PeekNextToken()
        while True:
            if nextToken[0]=='struct':
                self.structDeclar()
                nextToken=self.token.PeekNextToken()
            elif nextToken[1]=='Type' or nextToken[0]=='void':
                self.functionDeclar()
                nextToken=self.token.PeekNextToken()
            elif nextToken[1]!='EOF':
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"Unidentified token.")
            else:
                break


    def structDeclar(self):
        nextToken=self.token.GetNextToken()
        if nextToken[0]=="struct":
            nextToken=self.token.GetNextToken()
            if nextToken[1]=="Identifier":
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="{":
                    nextToken=self.token.PeekNextToken()
                    while nextToken[1]=='Type':
                        self.varDeclarStatement()
                        nextToken=self.token.PeekNextToken()
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]=="}":
                        pass
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"an identifier expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'struct' expected at this area")

    def functionDeclar(self):
        nextToken=self.token.GetNextToken()
        if nextToken[0]=="void" or nextToken[1]=="Type" or nextToken[1]=="Identifier":
            nextToken=self.token.GetNextToken()
            if nextToken[1]=="Identifier":
                self.generated += nextToken[0] + ":" + "\n"
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="(":
                    self.paramList()
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]==")":
                        nextToken=self.token.GetNextToken()
                        if nextToken[0]=="{":
                            while self.statementTest():
                                self.statement()
                            nextToken=self.token.GetNextToken()
                            if nextToken[0]=="}":
                                pass
                            else:
                                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                        else:
                            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area") 
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'(' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"an identifier expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a type at this area")

    # using to test whether it's possibly a statement by checking it's head  
    def statementTest(self):
        nextToken=self.token.PeekNextToken()
        if nextToken[0]=='if' or nextToken[1]=='Type' or nextToken[0]=='while' or nextToken[0]=='do' or nextToken[0]=='return' or nextToken[1]=='Identifier':
            return True
        else:
            return False

    def statement(self):
        nextToken=self.token.PeekNextToken()
        if nextToken[0]=='if':
            self.ifStatement()
        elif nextToken[1]=='Type':
            self.varDeclarStatement()
        elif nextToken[0]=='while':
            self.whileStatement()
        elif nextToken[0]=='do':
            self.doStatement()
        elif nextToken[0]=='return':
            self.returnStatement()
        elif nextToken[1]=='Identifier':
            self.assignStatement()
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a statement at this area")

    def paramList(self):
        nextToken=self.token.PeekNextToken()
        ident = ""
        if nextToken[1]=="Identifier":
            ident = nextToken[0]
            self.generated += str(self.line) + ": " + str(("dec", None, None, nextToken[0])) + "\n"
            self.line += 1
            nextToken=self.token.GetNextToken()
            nextToken=self.token.PeekNextToken()
            if nextToken[0]=="[":
                nextToken=self.token.GetNextToken()
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="]":
                    nextToken=self.token.PeekNextToken()
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"']' expected at this area")
            while nextToken[0]==',':
                nextToken=self.token.GetNextToken()
                nextToken=self.token.PeekNextToken()
                if nextToken[1]=="Identifier":
                    ident = nextToken[0]
                    self.generated += str(self.line) + ": " + str(("dec", None, None, nextToken[0])) + "\n"
                    self.line += 1
                    nextToken=self.token.GetNextToken()
                    nextToken=self.token.PeekNextToken()
                    if nextToken[0]=="[":
                        nextToken=self.token.GetNextToken()
                        self.expression()
                        nextToken=self.token.GetNextToken()
                        if nextToken[0]=="]":
                            nextToken=self.token.PeekNextToken()
                        else:
                            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"']' expected at this area")
            return ident


    def varDeclarStatement(self):
        nextToken=self.token.GetNextToken()
        if nextToken[0]=="void" or nextToken[1]=="Type" or nextToken[1]=="Identifier":
            nextToken=self.token.PeekNextToken()
            if nextToken[1]=="Identifier":
                ident = self.paramList()
                nextToken=self.token.PeekNextToken()
                if nextToken[0]=="=":
                    nextToken=self.token.GetNextToken()
                    self.expression()
                    nextToken=self.token.GetNextToken()
                    self.generated += str(self.line) + ": " + str(("=", "result", None, ident)) + "\n"
                    self.line += 1
                    if nextToken[0]==";":
                        pass
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"';' expected at this area")
                elif nextToken[0]==";":
                    nextToken=self.token.GetNextToken()
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"';' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"an identifier expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"a Type is expected at this area")

    def assignStatement(self):
        nextToken=self.token.GetNextToken()
        if nextToken[1]=="Identifier":
            ident = nextToken[0]
            nextToken=self.token.PeekNextToken()
            if nextToken[0]=="[":
                nextToken=self.token.GetNextToken()
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="]":
                    nextToken=self.token.PeekNextToken()
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"']' expected at this area")
            if nextToken[0]=="=":
                nextToken=self.token.GetNextToken()
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==";":
                    self.generated += str(self.line) + ": " + str(("=", "result", None, ident)) + "\n"
                    self.line += 1
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"';' expected at this area")
            elif nextToken[0]==";":
                nextToken=self.token.GetNextToken()
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"';' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"an identifier expected at this area")

    def ifStatement(self):
        nextToken=self.token.GetNextToken()
        if nextToken[0]=="if":
            nextToken=self.token.GetNextToken()
            if nextToken[0]=="(":
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==")":
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]=="{":
                        self.generated += str(self.line) + ": " + str(("if", "result", None, "")) + "\n"
                        self.line += 1
                        while self.statementTest():
                            self.statement()
                        nextToken=self.token.GetNextToken()
                        if nextToken[0]=="}":
                            nextToken=self.token.PeekNextToken()
                            if nextToken[0]=="else":
                                nextToken=self.token.GetNextToken()
                                nextToken=self.token.GetNextToken()
                                if nextToken[0]=="{":
                                    while self.statementTest():
                                        self.statement()
                                    nextToken=self.token.GetNextToken()
                                    if nextToken[0]=="}":
                                        pass
                                    else:
                                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                                else:
                                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
                        else:
                            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'(' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'if' expected at this area")

    def whileStatement(self):
        nextToken=self.token.GetNextToken()
        if nextToken[0]=="while":
            nextToken=self.token.GetNextToken()
            if nextToken[0]=="(":
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==")":
                    nextToken=self.token.GetNextToken()
                    if nextToken[0]=="{":
                        self.generated += str(self.line) + ": " + str(("while", "result", None, "")) + "\n"
                        self.line += 1
                        while self.statementTest():
                            self.statement()
                        nextToken=self.token.GetNextToken()
                        if nextToken[0]=="}":
                            pass
                        else:
                            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
                    else:
                        raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'(' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'while' expected at this area")

    def doStatement(self):
        nextToken=self.token.GetNextToken()
        if nextToken[0]=="do":
            nextToken=self.token.GetNextToken()
            if nextToken[0]=="{":
                self.generated += str(self.line) + ": " + str(("do", "result", None, "")) + "\n"
                self.line += 1
                while self.statementTest():
                    self.statement()
                nextToken=self.token.GetNextToken()
                if nextToken[0]=="}":
                    pass
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'}' expected at this area")
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'{' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'do' expected at this area")

    def expressionList(self):
        if self.factorTest():
            self.expression()
            while True:
                nextToken=self.token.PeekNextToken()
                if nextToken[0]==",":
                    nextToken=self.token.GetNextToken()
                    self.expression()
                else:
                    break

    def returnStatement(self):
        nextToken=self.token.GetNextToken()
        if nextToken[0]=="return":
            if self.factorTest():
                self.expression()
            nextToken=self.token.GetNextToken()
            if nextToken[0]==";":
                self.generated += str(self.line) + ": " + str(("return", "result", None, "")) + "\n"
                self.line += 1
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'return' expected at this area")
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"'return' expected at this area")

    def expression(self):
        if self.factorTest():
            self.relationalExpression()
            nextToken=self.token.PeekNextToken()
            while nextToken[0]=='&' or nextToken[0]=='|':
                op = nextToken[0]
                nextToken=self.token.GetNextToken()
                self.relationalExpression()
                self.generated += str(self.line) + ": " + str((op, "result1", "result2", "result")) + "\n"
                self.line += 1
                nextToken=self.token.PeekNextToken()
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a relational expression at this area")

    def relationalExpression(self):
        if self.factorTest():
            self.arithmeticExpression()
            nextToken=self.token.PeekNextToken()
            op = ""
            while True:
                # I think the provided full c grammar make a mistake here, so I correct it.
                if nextToken[0]=="=":
                    op += nextToken[0]
                    nextToken=self.token.GetNextToken()
                    self.arithmeticExpression()
                    nextToken=self.token.PeekNextToken()
                    self.generated += str(self.line) + ": " + str((op, "result1", "result2", "result")) + "\n"
                    self.line += 1
                elif nextToken[0]=='>' or nextToken[0]=='<':
                    nextToken=self.token.GetNextToken()
                    nextToken=self.token.PeekNextToken()
                    if nextToken[0]=="=":
                        op += nextToken[0]
                        nextToken=self.token.GetNextToken()
                    self.arithmeticExpression()
                    nextToken=self.token.PeekNextToken()
                    self.generated += str(self.line) + ": " + str((op, "result1", "result2", "result")) + "\n"
                    self.line += 1
                else:
                    break
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a arithmetic expression at this area")

    def arithmeticExpression(self):
        if self.factorTest():
            self.term()
            nextToken=self.token.PeekNextToken()
            while nextToken[0]=='+' or nextToken[0]=='-':
                op = nextToken[0]
                nextToken=self.token.GetNextToken()
                self.term()
                nextToken=self.token.PeekNextToken()
                self.generated += str(self.line) + ": " + str((op, "result1", "result2", "result")) + "\n"
                self.line += 1
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a term at this area")

    def term(self):
        if self.factorTest():
            self.factor()
            nextToken=self.token.PeekNextToken()
            while nextToken[0]=='*' or nextToken[0]=='/':
                op = nextToken[0]
                nextToken=self.token.GetNextToken()
                self.factor()
                nextToken=self.token.PeekNextToken()
                self.generated += str(self.line) + ": " + str((op, "result1", "result2", "result")) + "\n"
                self.line += 1
        else:
            raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a factor at this area")

    def factor(self):
        nextToken=self.token.PeekNextToken()
        preOP = ""
        if nextToken[0]=='-' or nextToken[0]=='!':
            preOP = nextToken[0]
            nextToken=self.token.GetNextToken()
        nextToken=self.token.GetNextToken()
        if nextToken[1]=='Integer' or nextToken[1]=='String' or nextToken[0]=='true' or nextToken[0]=='false' or nextToken[0]=='null' or nextToken[0]=='this':
            self.generated += str(self.line) + ": " + str(("=", nextToken[0], None, "result")) + "\n"
            self.line += 1
        elif nextToken[1]=='Identifier':
            self.generated += str(self.line) + ": " + str(("=", nextToken[0], None, "result")) + "\n"
            self.line += 1
            nextToken=self.token.PeekNextToken()
            if nextToken[0]=='.':
                nextToken=self.token.GetNextToken()
                nextToken=self.token.GetNextToken()
                if nextToken[1]=='Identifier':
                    pass
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"expect a identifier at this area")
                nextToken=self.token.PeekNextToken()
            if nextToken[0]=='[':
                nextToken=self.token.GetNextToken()
                self.expression()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==']':
                    pass
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"']' expected at this area")
            if nextToken[0]=='(':
                nextToken=self.token.GetNextToken()
                self.expressionList()
                nextToken=self.token.GetNextToken()
                if nextToken[0]==')':
                    pass
                else:
                    raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")
        elif nextToken[0]=='(':
            self.expressionList()
            nextToken=self.token.GetNextToken()
            if nextToken[0]==')':
                pass
            else:
                raise Exception('Syntax',self.token.line+1,self.token.code[self.token.line][self.token.pointer-1],"')' expected at this area")

        if preOP:
            self.generated += str(self.line) + ": " + str((preOP, None, "result", "result")) + "\n"
            self.line += 1
        
    # using to test whether it's possibly an expression by checking it's head        
    def factorTest(self):
        nextToken=self.token.PeekNextToken()
        if nextToken[1]=='Integer' or nextToken[0]=='-' or nextToken[0]=='!' or nextToken[1]=='String' or nextToken[0]=='true' or nextToken[0]=='false' or nextToken[0]=='null' or nextToken[0]=='this' or nextToken[1]=='Identifier' or nextToken[0]=='(':
            return True
        else:
            return False
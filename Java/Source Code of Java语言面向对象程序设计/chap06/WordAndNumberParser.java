import java.io.*;
public class WordAndNumberParser{
    public static void main(String[] args) throws IOException{
        if(args.length<1) {
            System.out.println("Usage: java WordAndNumberParser <filename>");
            System.exit(0);
        }
        WordAndNumberParser app=new WordAndNumberParser();
        app.parseFile(args[0]);
    }
    private void parseFile(String filename){
        int wordCount=0;
        int numberCount=0;
        try (FileReader reader=new FileReader(filename);){
            StreamTokenizer tokenizer=new StreamTokenizer(reader);
            tokenizer.slashSlashComments(true);
            tokenizer.slashStarComments(true);
            while(tokenizer.nextToken()!=StreamTokenizer.TT_EOF) {
                if(tokenizer.ttype==StreamTokenizer.TT_WORD){
                    wordCount++;
                }else if(tokenizer.ttype==StreamTokenizer.TT_NUMBER){
                    numberCount++;
                }
                if(tokenizer.sval!=null && tokenizer.sval.equals("DataInputStream")){
                    System.out.println(tokenizer.toString());
                }
             }
         }catch(FileNotFoundException fe){
             System.out.println("File not found: "+filename);
             return;
         }catch(IOException ioe){
             System.out.println("Error parseing file");
         }
         System.out.println("Number of words: "+wordCount);
         System.out.println("Number of numerals: "+numberCount);
    }
}
                    
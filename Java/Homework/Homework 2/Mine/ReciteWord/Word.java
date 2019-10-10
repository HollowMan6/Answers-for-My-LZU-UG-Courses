import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class Word {
	// 第一个记录word.txt，第二个记录wrong.txt，第三个记录mark.txt
	private long[] pstline;
	private long wdline;
	private long wrline;
	private long mkline;
	private String enword;
	private String cntrans;

	public Word() throws IOException {
		File f1 = new File("history.dat");
		if (f1.exists())
			pstline = readRecord();
		else
			pstline = new long[3];
		enword = "";
		cntrans = "";
		File wd = new File("word.txt");
		File wr = new File("wrong.txt");
		File mk = new File("mark.txt");
		if (wd.exists()) {
			LineNumberReader lnr = new LineNumberReader(new FileReader(wd));
			lnr.skip(Long.MAX_VALUE);
			wdline = lnr.getLineNumber();
			lnr.close();
		} else
			wdline = 0;
		if (wr.exists()) {
			LineNumberReader lnr = new LineNumberReader(new FileReader(wr));
			lnr.skip(Long.MAX_VALUE);
			wrline = lnr.getLineNumber();
			lnr.close();
		} else
			wrline = 0;
		if (mk.exists()) {
			LineNumberReader lnr = new LineNumberReader(new FileReader(mk));
			lnr.skip(Long.MAX_VALUE);
			mkline = lnr.getLineNumber();
			lnr.close();
		} else
			mkline = 0;

	}

	public String getEnWord() {
		return enword;
	}

	public String getCnTrans() {
		return cntrans;
	}

	public boolean setPstLine(long word, long wrong, long mark) {
		if (word <= wdline && wrong <= wrline && mkline <= mkline) {
			pstline[0] = word;
			pstline[1] = wrong;
			pstline[2] = mark;
			return true;
		} else
			return false;
	}

	public boolean setPstLine(long[] record) {
		if (record.length == 3) {

			if (record[0] <= wdline && record[1] <= wrline && record[2] <= mkline) {
				pstline = record;
				return true;
			} else
				return false;
		} else
			return false;
	}

	public long getwordLine() {
		return pstline[0];
	}

	public long getwrongLine() {
		return pstline[1];
	}

	public long getmarkLine() {
		return pstline[2];
	}

	public long getwordTotalLine() throws IOException {
		File wd = new File("word.txt");
		if (wd.exists()) {
			LineNumberReader lnr = new LineNumberReader(new FileReader(wd));
			lnr.skip(Long.MAX_VALUE);
			wdline = lnr.getLineNumber();
			lnr.close();
		} else
			wdline = 0;
		return wdline;
	}

	public long getwrongTotalLine() throws IOException {
		File wr = new File("wrong.txt");
		if (wr.exists()) {
			LineNumberReader lnr = new LineNumberReader(new FileReader(wr));
			lnr.skip(Long.MAX_VALUE);
			wrline = lnr.getLineNumber();
			lnr.close();
		} else
			wrline = 0;
		return wrline;
	}

	public long getmarkTotalLine() throws IOException {
		File mk = new File("mark.txt");
		if (mk.exists()) {
			LineNumberReader lnr = new LineNumberReader(new FileReader(mk));
			lnr.skip(Long.MAX_VALUE);
			mkline = lnr.getLineNumber();
			lnr.close();
		} else
			mkline = 0;
		return mkline;
	}

	public boolean readWord(String choice) {
		File file;
		int choicenum;
		if (choice.equals("word")) {
			file = new File(choice + ".txt");
			choicenum = 0;
		} else if (choice.equals("wrong")) {
			file = new File(choice + ".txt");
			choicenum = 1;
		} else if (choice.equals("mark")) {
			file = new File(choice + ".txt");
			choicenum = 2;
		} else
			return false;
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			for (long i = 0; i < pstline[choicenum] * 2; i++)
				sc.next();
			if (sc.hasNext()) {
				enword = sc.next();
				cntrans = sc.next();
				pstline[choicenum]++;
			} else {
				sc.close();
				return false;
			}
			sc.close();
			return true;
		} catch (Exception e) {
			sc.close();
			return false;
		}
	}

	public boolean judge(String input, boolean r) throws IOException {
		if (input.equals(enword)) {
			return true;
		} else {
			if (r) {
				FileOutputStream fos = new FileOutputStream(new File("wrong.txt"), true);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(enword + " " + cntrans + "\n");
				bw.close();
				osw.close();
				fos.close();
			}
			wrline++;
			return false;
		}
	}

	public void mark() throws IOException {
		FileOutputStream fos = new FileOutputStream(new File("mark.txt"), true);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(enword + " " + cntrans + "\n");
		bw.close();
		osw.close();
		fos.close();
		mkline++;
	}

	public void saveRecord() throws IOException {
		FileOutputStream out1 = new FileOutputStream("history.dat");
		BufferedOutputStream out2 = new BufferedOutputStream(out1);
		DataOutputStream out = new DataOutputStream(out2);
		out.writeLong(pstline[0]);
		out.writeLong(pstline[1]);
		out.writeLong(pstline[2]);
		out.close();
	}

	public long[] readRecord() throws IOException {
		InputStream in1 = new FileInputStream("history.dat");
		BufferedInputStream in2 = new BufferedInputStream(in1);
		DataInputStream in = new DataInputStream(in2);
		long record[] = new long[3];
		record[0] = in.readLong();
		record[1] = in.readLong();
		record[2] = in.readLong();
		in.close();
		return record;
	}
}
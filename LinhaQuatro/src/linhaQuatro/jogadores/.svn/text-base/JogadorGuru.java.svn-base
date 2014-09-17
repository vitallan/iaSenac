package linhaQuatro.jogadores;

import java.io.*;

public class JogadorGuru implements Jogador{
    
        private ConnectFour_Bits board;
        Chrono timecounter;
        char[] ba_database;
        
    
        public JogadorGuru(){
            //Inicializacao do Jogador
            board = new ConnectFour_Bits();
            readFileToMemory();
            return;
        }
        
        public void readFileToMemory(){
            long init = System.nanoTime();
            CharArrayWriter chartemp = new CharArrayWriter(8*1024);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream(8*1024);
            new PlayDecoder().decode("db_8ply.txt", bytestream);
            ByteArrayInputStream bytearray = new ByteArrayInputStream(bytestream.toByteArray());
            int ch;
            while ((ch = bytearray.read()) != -1){
                chartemp.write(ch);
            }
        ba_database = chartemp.toCharArray();
        }
        
        
        public void loadFile(){
        //long init = System.nanoTime();
        //Read file
        BufferedReader dis = null;
        dis = new BufferedReader(new CharArrayReader(ba_database));

        //int workcounter = 0;
        board.tt_hash_clear();
        for (;;) {
            String line = null;
            try {
                line = dis.readLine();
            
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
				
            }
            
            if (line == null) {
                break;
            }
            board.set_all_zero();
            int pos=0;
            int score = 0;
            int depthlog = 0;
            for (int i = 0; i < line.length(); i++) {
                if ( ((line.charAt(i) - '1') > 10) || ((line.charAt(i) - '1') < 0) ){
                    pos++;
                }
                if (pos==0){
                    board.makemove(line.charAt(i) - '1');
                }else if(pos==1){
                    score = "#-<=>+".indexOf(line.charAt(i));
                    pos++;
                }else if (pos==2){
                    depthlog = Integer.parseInt(line.substring(i, line.length()));
                    pos++;
                }
            }
                board.tt_hash();
                board.tt_store(board.hashtable_index, board.key, score, depthlog);
        }
        board.set_all_zero();
    }
	
	public String getNome(){
            long a = System.currentTimeMillis();
            System.gc();
            return "Esoterico";
        }
	
	public int jogada(int[][] tabuleiro, int corDaMinhaBola){
                
                //Clear old used bitboard
                int i=0,j=0;
                while (i<7){
                    if (tabuleiro[ 5 ][ i ] == 0){
                        j++;
                    }
                    if (tabuleiro[ 6 ][ i ] == 0){
                        j++;
                    }
                    i++;
                }
                if (j==14 && corDaMinhaBola == 1){
                    loadFile();
                }else if (j==13 && corDaMinhaBola == 2){
                    loadFile();
                }               
                
                
                //int i=0;
                i=0;
                while (i<7){
                    int tab_height = (6 - ((board.curr_col_height[i]) & ConnectFour_Bits.HEIGHT));
                    if (tab_height == -1){
                        i++;
                        continue;
                    }
                    if (tabuleiro[ tab_height ][ i ] != 0){
                        board.makemove(i);
                        break;
                    }
                    i++;
                }
                       
        if (board.num_plays < 1){
            int play = (int) (Math.random() * 100) % 3 + 2; // Plays only on the 3 center spots (lack of dictonary !!)
            board.makemove(play);
            return play;
        }
        if (board.num_plays < 4 && corDaMinhaBola == 2){
            if (board.moves[0] == 1 || board.moves[0] == 5){
                board.makemove(3);
                return 3;
            }
			if (board.moves[0] == 0 || board.moves[0] == 6){
				board.makemove(3);
				return 3;
			}
        }
        
        if (board.num_plays < 9){
            int result = board.get_best_move();

            if (result == -1) {
                //Game Is Lost
                 result = (int) (Math.random() * 100) % 7;
                while (!(board.isplayable(result))) {
                    result = (int) (Math.random() * 100) % 7;
                }
                
            }
            board.makemove(result);
            return result;
        }
        /*
         * 282582357508353 => 0
         * 282582358032643 => 1,2,4
         */
        // HARD CODED PLAYS ( 4444444333 moves)
        if (board.board_hash() == 282582357508353L){
            int result;
            do{
               result = (int) (Math.random() * 100) % 7;
            }while (result == 2 || result == 3 || result == 4 || result == 5);
            board.makemove(result);return result;
        }else if (board.board_hash() == 282582358032643L){
            int result;
            do{
               result = (int) (Math.random() * 100) % 7;
            }while (result == 0 || result == 3 || result == 6);
            board.makemove(result);return result;
        }else if (board.board_hash() == 295777318732545L){
            board.makemove(5);
            return 5;
        }
        
            
        board.tt_hash_clear();
        int result = board.get_best_move();
        
        if (result == -1){
            
                //Game Is Lost
                 result = (int) (Math.random() * 100) % 7;
                while (!(board.isplayable(result))) {
                    result = (int) (Math.random() * 100) % 7;
                }
                
        }
        board.makemove(result);
        return result;
        }
        
        
        
        private class ConnectFour_Bits {
        /* Every implementation was outdated or created to be used in a 7x6 board format.
         * Modifications were made to support 7x7 boards and since to improve over outdated versions
        
        Bitboard implementation by Martin Fierz
        Hash Table Implementation and Alpha-Beta Prune Search by John Tromp
         */
        //hash table constants

        static final int KEYSIZE = 26;
        static final int SCOREKEYSIZE = 29;
        static final int HASHSIZE = 8306069;
        static final int SYMMETRIC_MAX_DEPTH = 10; //Test Symmetry up to this depth

        static final int SCOREKEYMASK = (1 << SCOREKEYSIZE) - 1;
        static final int KEYMASK = (1 << KEYSIZE) - 1;

        // alpha-beta values
        static final int UNKNOWN = 0;
        static final int LOSS = 1;
        static final int DRAWLOSS = 2;
        static final int DRAW = 3;
        static final int DRAWWIN = 4;
        static final int WIN = 5;
        static final int LOSSWIN = 6;
        protected long num_position_stored;
        protected int hashtable[];
        protected int hashtable_index,  key;
        static final int WIDTH = 7;
        static final int HEIGHT = 7;
        static final int H1 = 8;                     // Height + TOP ROW
        static final int SIZE = 49;                  // Board Size
        static final int SIZE1 = 56;                 // Board Size + TOP ROW
        static final long ALL1 = 72057594037927935L; //11111111 11111111 11111111 11111111 11111111 11111111 11111111
        static final int COL1 = 255;                 //11111111  
        static final long BOTTOMMASK = 282578800148737L; //00000001 00000001 00000001 00000001 00000001 00000001 00000001
        static final long TOPMASK = 36170086419038336L;  //10000000 10000000 10000000 10000000 10000000 10000000 10000000

        long color[];  // Player 1 / Player 2 - bitboard

        int moves[], num_plays;
        byte curr_col_height[]; //lowest possible byte to add in each column

        //long nodes;
        public ConnectFour_Bits() {
            hashtable = new int[16612138];
            color = new long[2];
            curr_col_height = new byte[WIDTH];
            moves = new int[SIZE];
            set_all_zero();
        }

        @Deprecated
        private void print_long_in_board_form(long longvalue) {
            String a = Long.toBinaryString(longvalue);
            int[][] board = new int[7][8];
            int i, j;
            for (i = 0; i < 7; i++) {
                for (j = 0; j < 8; j++) {
                    try {
                        board[i][7 - j] = Integer.parseInt(a.valueOf(a.charAt(a.length() - 1 - (i * 8 + j))));
                    } catch (Exception e) {
                        board[i][7 - j] = 0;
                    }
                }
            }
            for (i = 0; i < 8; i++) {
                for (j = 0; j < 7; j++) {
                    System.out.print(board[j][i]);
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }

        @Deprecated
        private long string_to_long(String board_str) {
            long board = 0L;
            int[][] board_array = new int[8][7];
            String tolong = "";

            int z = 0;
            while (z < 56) {
                board_array[z / 7][z % 7] = Integer.parseInt("" + board_str.charAt(z));
                z++;
            }
            int i = 0, j = 0;
            for (i = 6; i >= 0; i--) {
                for (j = 0; j < 8; j++) {
                    tolong += board_array[j][i];
                }
            }
            board = Long.parseLong(tolong, 2);
            return board;
        }

        void set_all_zero() {
            //Sets current height to BOTTOM ROW
            for (int i = 0; i < WIDTH; i++) {
                curr_col_height[i] = (byte) (H1 * i);
            }
            num_plays = 0;
            color[0] = color[1] = 0L;
        }

        public long board_hash() {
            return color[num_plays & 1] + color[0] + color[1] + BOTTOMMASK;
        }
        
        public String toString() {
            StringBuffer buf = new StringBuffer();

            for (int i = 0; i < num_plays; i++) {
                buf.append(1 + moves[i]);
            }
            return buf.toString();

        }

        // can add more pieces in col
        final boolean isplayable(int col) {
            return islegal(color[num_plays & 1] | (1L << curr_col_height[col]));
        }

        // is board overflowing (have more pieces in column than avaible spaces)
        final boolean islegal(long newboard) {
            return (newboard & TOPMASK) == 0;
        }

        // newboard is possible and player has won
        final boolean islegalhaswon(long newboard) {
            return islegal(newboard) && haswon(newboard);
        }

        // return whether newboard includes a win
        final boolean haswon(long newboard) {

            long y = newboard & (newboard >> HEIGHT);
            if ((y & (y >> (HEIGHT << 1))) != 0) // check left(\) diagonal
            {
                return true;
            }
            y = newboard & (newboard >> H1);
            if ((y & (y >> (H1 << 1))) != 0) // check horizontal(-)
            {
                return true;
            }
            y = newboard & (newboard >> 9); // check right(/) diagonal 

            if ((y & (y >> 18)) != 0) {
                return true;
            }

            y = newboard & (newboard >> 1); // check vertical(|)

            return (y & (y >> 2)) != 0;
        }

        void backmove() {
            int n;
            // Get Last Column Played
            n = moves[--num_plays];
            // Remove piece from P1&P2 Board
            color[num_plays & 1] ^= 1L << --curr_col_height[n];
        }

        void makemove(int n) {
            // Add piece to P1&P2 Board by inverting last played bit
            color[num_plays & 1] ^= 1L << curr_col_height[n]++;
            // Add Last Column Played
            moves[num_plays++] = n;
        }

        void tt_hash_clear() {
            for (int i = 0; i < 16612138; i++) {
                hashtable[i] = 0;
            }
            num_position_stored = 0L;
        }

        void tt_hash() {
            long htemp = board.board_hash();
            if (board.num_plays < SYMMETRIC_MAX_DEPTH) {
                long htemp2 = 0L;
                for (long htmp = htemp; htmp != 0; htmp >>= H1) {
                    htemp2 = htemp2 << H1 | (htmp & COL1);
                }
                if (htemp2 < htemp) {
                    htemp = htemp2;
                }
            }
            key = (int) (htemp >> 30);
            hashtable_index = ((int) (htemp % HASHSIZE)) << 1;
        }

        int tt_get() {
            tt_hash();
            int he0 = hashtable[hashtable_index];
            int he1 = hashtable[hashtable_index + 1];
            if ((he0 & KEYMASK) == key) {
                return he1 >>> SCOREKEYSIZE;
            }
            if ((he1 & KEYMASK) == key) {
                return (he1 >> KEYSIZE) & 7;
            }
            return UNKNOWN; // Not Found in hash

        }

        void tt_store(int pos, int key, int score, int depth) {
            int he0 = hashtable[pos];
            int he1 = hashtable[pos + 1];
            int biglock = he0 & KEYMASK;
            if (biglock == key || depth >= (he0 >>> KEYSIZE)) {
                hashtable[pos] = (depth << KEYSIZE) | key;
                hashtable[pos + 1] = (score << SCOREKEYSIZE) | (he1 & SCOREKEYMASK);
            } else {
                hashtable[pos + 1] = ((he1 >>> SCOREKEYSIZE) << 3 | score) << KEYSIZE | key;
            }
            num_position_stored++;
        }
        int history[][] = new int[2][SIZE1];
        int bestmove = -1;
        

        void heuristic_history() {
            for (int side = 0; side < 2; side++) {
                history[side][27]=16;
                history[side][19]=history[side][26]=history[side][28]=history[side][35]=13;
                history[side][18]=history[side][20]=history[side][34]=history[side][36]=11;
                history[side][11]=history[side][25]=history[side][29]=history[side][43]=10;
                history[side][10]=history[side][12]=history[side][17]=history[side][21]=history[side][33]=history[side][37]=history[side][42]=history[side][44]=8;
                history[side][9]=history[side][13]=history[side][41]=history[side][45]=6;
                history[side][3]=history[side][24]=history[side][30]=history[side][51]=7;
                history[side][2]=history[side][4]=history[side][16]=history[side][22]=history[side][32]=history[side][38]=history[side][50]=history[side][52]=5;
                history[side][1]=history[side][5]=history[side][8]=history[side][14]=history[side][40]=history[side][46]=history[side][49]=history[side][53]=4;
                history[side][0]=history[side][6]=history[side][48]=history[side][54]=3;
                history[side][7]=history[side][15]=history[side][23]=history[side][31]=history[side][39]=history[side][47]=history[side][55]=0;
            }
        }

        int ab(int depth, int alpha, int beta) {
            if (timecounter.getdiff() > 9100000000L) {
                return 0;
            }
            if (num_plays == SIZE - 1) {
                return DRAW; // by assumption, player to move can't win

            }
            int player, other_player;
            other_player = (player = num_plays & 1) ^ 1;
            long other = color[other_player];
            int i, nav, av[] = new int[WIDTH];
            long newbrd;
            boolean winontop;
            for (i = nav = 0; i < WIDTH; i++) {
                newbrd = other | (1L << curr_col_height[i]);

                if (!islegal(newbrd)) {
                    continue;
                }
                winontop = islegalhaswon(other | (2L << curr_col_height[i]));
                if (haswon(newbrd)) { // immediate threat

                    if (winontop) // can't stop double threat
                    {
                        if (depth == 0) {
                            bestmove = i;
                        }
                        return LOSS;
                    }
                    nav = 0; // forced move

                    av[nav++] = i;
                    while (++i < WIDTH) {
                        if (islegalhaswon(other | (1L << curr_col_height[i]))) {
                            if (depth == 0) {
                                bestmove = i;
                            }
                            return LOSS;
                        }
                    }
                    break;
                }
                if (!winontop) {
                    av[nav++] = i;
                }
            }
            if (nav == 0) {
                return LOSS;
            }
            if (num_plays == SIZE - 2) // two moves left
            {
                return DRAW; // opponent has no win either

            }
            int score = 0;
            if (nav == 1) {
                if (depth == 0) {
                    bestmove = av[0];
                } else {
                    makemove(av[0]);
                    score = LOSSWIN - ab(depth + 1, LOSSWIN - beta, LOSSWIN - alpha);
                    backmove();
                }
                return score;
            }
            int ttscore;
            if (depth == 0) {
                ttscore = UNKNOWN;
            } else {
                ttscore = tt_get();
            }
            if (ttscore != UNKNOWN) {
                if (ttscore == DRAWLOSS) {
                    if ((beta = DRAW) <= alpha) {
                        return ttscore;
                    }
                } else if (ttscore == DRAWWIN) {
                    if ((alpha = DRAW) >= beta) {
                        return ttscore;
                    }
                } else {
                    return ttscore; // exact score

                }
            }
            int hashindx = hashtable_index;
            int hashlock = key;
            long poscnt = num_position_stored;
            int besti = 0, j, l, sc;
            int v, val;
            score = LOSS;
            for (i = 0; i < nav; i++) {
                val = history[player][curr_col_height[av[l = i]]];
                for (j = i + 1; j < nav; j++) {
                    v = history[player][curr_col_height[av[j]]];
                    if (v > val) {
                        val = v;
                        l = j;
                    }
                }
                for (j = av[l]; l > i; l--) {
                    av[l] = av[l - 1];
                }
                makemove(av[i] = j);
                sc = LOSSWIN - ab(depth + 1, LOSSWIN - beta, LOSSWIN - alpha);
                backmove();
                if (sc > score) {
                    besti = i;
                    if ((score = sc) > alpha && (alpha = sc) >= beta) {
                        if (score == DRAW && i < nav - 1) {
                            score = DRAWWIN;
                        }
                        if (besti > 0) {
                            for (i = 0; i < besti; i++) {
                                history[player][curr_col_height[av[i]]]--; // punish worse

                            }
                            history[player][curr_col_height[av[besti]]] += besti;
                        }
                        break;
                    }
                }
            }
            if (score == LOSSWIN - ttscore) // combine < and >
            {
                score = DRAW;
            }
            poscnt = num_position_stored - poscnt;
            int logdepth;
            for (logdepth = 0; (poscnt >>= 1) != 0; logdepth++);

            tt_store(hashindx, hashlock, score, logdepth);

            if (depth == 0) {
                bestmove = av[besti];
            }
            return score;
        }
        
        int get_best_move() {
            bestmove = -1;
            int i, side = num_plays & 1, otherside = side ^ 1;
            if (haswon(color[otherside])) {
                return -1;
            }
            for (i = 0; i < WIDTH; i++) {
                if (islegalhaswon(color[side] | (1L << curr_col_height[i]))) {
                    return i;
                }
                if ( islegalhaswon( (color[otherside] | (1L << curr_col_height[i]) ) ) ) {
                    //Immediate Threat
                    bestmove = i;
                }
            }
            if (bestmove!=-1){
                return bestmove;
            }
            
            heuristic_history();
            //long currenttime = System.nanoTime();
            
            timecounter = new Chrono();
            timecounter.start();
            int score = ab(0, LOSS, WIN);
            timecounter.disable();
            timecounter = null;

            return bestmove;
        }
    }

        private class Chrono extends Thread {

        private long starttime;
        private long difftime;
        private boolean keepalive = true;

        public Chrono() {
            starttime = System.nanoTime();
        }

        private void prepare_run() {
            starttime = System.nanoTime();
        }

        @Override
        public void run() {
            prepare_run();
            while (keepalive) {
                difftime = System.nanoTime() - starttime;
                try {
                    sleep(200);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(JogadorPerfectPlay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public long getdiff() {
            return difftime;
        }

        public void disable() {
            keepalive = false;
        }
    }
}



class PlayDecoder {

    public void decode(String Filein, java.io.OutputStream streamout) {

        java.io.File inFile = new java.io.File(Filein);
        //java.io.File outFile = new java.io.File(out);

        try{
        java.io.BufferedInputStream inStream = new java.io.BufferedInputStream(new java.io.FileInputStream(inFile));
        //java.io.BufferedOutputStream outStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(outFile));
        java.io.BufferedOutputStream outStream = new java.io.BufferedOutputStream(streamout);
       
        //java.io.CharArrayWriter outStream =  new java.io.CharArrayWriter(8*1024);

        int propertiesSize = 5;
        byte[] properties = new byte[propertiesSize];
        if (inStream.read(properties, 0, propertiesSize) != propertiesSize) {
            throw new Exception("input file is corrupted");
        }
        Decoder decoder = new Decoder();
        if (!decoder.SetDecoderProperties(properties)) {
            throw new Exception("Incorrect stream properties");
        }
        long outSize = 0;
        for (int i = 0; i < 8; i++) {
            int v = inStream.read();
            if (v < 0) {
                throw new Exception("Can't read stream size");
            }
            outSize |= ((long) v) << (8 * i);
        }
        if (!decoder.Code(inStream, outStream, outSize)) {
            throw new Exception("Error in data stream");
        }
        outStream.flush();
        outStream.close();
        inStream.close();
        }catch (Exception e){
            System.out.println("File not Found OR Error Decoding File.");
            System.exit(0);
        }

    }
}
    
class Decoder
{
    /* LZMA  1999-2006 Igor Pavlov */
	class LenDecoder
	{
		short[] m_Choice = new short[2];
		BitTreeDecoder[] m_LowCoder = new BitTreeDecoder[Base.kNumPosStatesMax];
		BitTreeDecoder[] m_MidCoder = new BitTreeDecoder[Base.kNumPosStatesMax];
		BitTreeDecoder m_HighCoder = new BitTreeDecoder(Base.kNumHighLenBits);
		int m_NumPosStates = 0;
		
		public void Create(int numPosStates)
		{
			for (; m_NumPosStates < numPosStates; m_NumPosStates++)
			{
				m_LowCoder[m_NumPosStates] = new BitTreeDecoder(Base.kNumLowLenBits);
				m_MidCoder[m_NumPosStates] = new BitTreeDecoder(Base.kNumMidLenBits);
			}
		}
		
		public void Init()
		{
			RangeDecoder.InitBitModels(m_Choice);
			for (int posState = 0; posState < m_NumPosStates; posState++)
			{
				m_LowCoder[posState].Init();
				m_MidCoder[posState].Init();
			}
			m_HighCoder.Init();
		}
		
		public int Decode(RangeDecoder rangeDecoder, int posState) throws IOException
		{
			if (rangeDecoder.DecodeBit(m_Choice, 0) == 0)
				return m_LowCoder[posState].Decode(rangeDecoder);
			int symbol = Base.kNumLowLenSymbols;
			if (rangeDecoder.DecodeBit(m_Choice, 1) == 0)
				symbol += m_MidCoder[posState].Decode(rangeDecoder);
			else
				symbol += Base.kNumMidLenSymbols + m_HighCoder.Decode(rangeDecoder);
			return symbol;
		}
	}
	
	class LiteralDecoder
	{
		class Decoder2
		{
			short[] m_Decoders = new short[0x300];
			
			public void Init()
			{
				RangeDecoder.InitBitModels(m_Decoders);
			}
			
			public byte DecodeNormal(RangeDecoder rangeDecoder) throws IOException
			{
				int symbol = 1;
				do
					symbol = (symbol << 1) | rangeDecoder.DecodeBit(m_Decoders, symbol);
				while (symbol < 0x100);
				return (byte)symbol;
			}
			
			public byte DecodeWithMatchByte(RangeDecoder rangeDecoder, byte matchByte) throws IOException
			{
				int symbol = 1;
				do
				{
					int matchBit = (matchByte >> 7) & 1;
					matchByte <<= 1;
					int bit = rangeDecoder.DecodeBit(m_Decoders, ((1 + matchBit) << 8) + symbol);
					symbol = (symbol << 1) | bit;
					if (matchBit != bit)
					{
						while (symbol < 0x100)
							symbol = (symbol << 1) | rangeDecoder.DecodeBit(m_Decoders, symbol);
						break;
					}
				}
				while (symbol < 0x100);
				return (byte)symbol;
			}
		}
		
		Decoder2[] m_Coders;
		int m_NumPrevBits;
		int m_NumPosBits;
		int m_PosMask;
		
		public void Create(int numPosBits, int numPrevBits)
		{
			if (m_Coders != null && m_NumPrevBits == numPrevBits && m_NumPosBits == numPosBits)
				return;
			m_NumPosBits = numPosBits;
			m_PosMask = (1 << numPosBits) - 1;
			m_NumPrevBits = numPrevBits;
			int numStates = 1 << (m_NumPrevBits + m_NumPosBits);
			m_Coders = new Decoder2[numStates];
			for (int i = 0; i < numStates; i++)
				m_Coders[i] = new Decoder2();
		}
		
		public void Init()
		{
			int numStates = 1 << (m_NumPrevBits + m_NumPosBits);
			for (int i = 0; i < numStates; i++)
				m_Coders[i].Init();
		}
		
		Decoder2 GetDecoder(int pos, byte prevByte)
		{
			return m_Coders[((pos & m_PosMask) << m_NumPrevBits) + ((prevByte & 0xFF) >>> (8 - m_NumPrevBits))];
		}
	}
	
	OutWindow m_OutWindow = new OutWindow();
	RangeDecoder m_RangeDecoder = new RangeDecoder();
	
	short[] m_IsMatchDecoders = new short[Base.kNumStates << Base.kNumPosStatesBitsMax];
	short[] m_IsRepDecoders = new short[Base.kNumStates];
	short[] m_IsRepG0Decoders = new short[Base.kNumStates];
	short[] m_IsRepG1Decoders = new short[Base.kNumStates];
	short[] m_IsRepG2Decoders = new short[Base.kNumStates];
	short[] m_IsRep0LongDecoders = new short[Base.kNumStates << Base.kNumPosStatesBitsMax];
	
	BitTreeDecoder[] m_PosSlotDecoder = new BitTreeDecoder[Base.kNumLenToPosStates];
	short[] m_PosDecoders = new short[Base.kNumFullDistances - Base.kEndPosModelIndex];
	
	BitTreeDecoder m_PosAlignDecoder = new BitTreeDecoder(Base.kNumAlignBits);
	
	LenDecoder m_LenDecoder = new LenDecoder();
	LenDecoder m_RepLenDecoder = new LenDecoder();
	
	LiteralDecoder m_LiteralDecoder = new LiteralDecoder();
	
	int m_DictionarySize = -1;
	int m_DictionarySizeCheck =  -1;
	
	int m_PosStateMask;
	
	public Decoder()
	{
		for (int i = 0; i < Base.kNumLenToPosStates; i++)
			m_PosSlotDecoder[i] = new BitTreeDecoder(Base.kNumPosSlotBits);
	}
	
	boolean SetDictionarySize(int dictionarySize)
	{
		if (dictionarySize < 0)
			return false;
		if (m_DictionarySize != dictionarySize)
		{
			m_DictionarySize = dictionarySize;
			m_DictionarySizeCheck = Math.max(m_DictionarySize, 1);
			m_OutWindow.Create(Math.max(m_DictionarySizeCheck, (1 << 12)));
		}
		return true;
	}
	
	boolean SetLcLpPb(int lc, int lp, int pb)
	{
		if (lc > Base.kNumLitContextBitsMax || lp > 4 || pb > Base.kNumPosStatesBitsMax)
			return false;
		m_LiteralDecoder.Create(lp, lc);
		int numPosStates = 1 << pb;
		m_LenDecoder.Create(numPosStates);
		m_RepLenDecoder.Create(numPosStates);
		m_PosStateMask = numPosStates - 1;
		return true;
	}
	
	void Init() throws IOException
	{
		m_OutWindow.Init(false);
		
		RangeDecoder.InitBitModels(m_IsMatchDecoders);
		RangeDecoder.InitBitModels(m_IsRep0LongDecoders);
		RangeDecoder.InitBitModels(m_IsRepDecoders);
		RangeDecoder.InitBitModels(m_IsRepG0Decoders);
		RangeDecoder.InitBitModels(m_IsRepG1Decoders);
		RangeDecoder.InitBitModels(m_IsRepG2Decoders);
		RangeDecoder.InitBitModels(m_PosDecoders);
		
		m_LiteralDecoder.Init();
		int i;
		for (i = 0; i < Base.kNumLenToPosStates; i++)
			m_PosSlotDecoder[i].Init();
		m_LenDecoder.Init();
		m_RepLenDecoder.Init();
		m_PosAlignDecoder.Init();
		m_RangeDecoder.Init();
	}
	
	public boolean Code(java.io.InputStream inStream, java.io.OutputStream outStream,
			long outSize) throws IOException
	{
		m_RangeDecoder.SetStream(inStream);
		m_OutWindow.SetStream(outStream);
		Init();
		
		int state = Base.StateInit();
		int rep0 = 0, rep1 = 0, rep2 = 0, rep3 = 0;
		
		long nowPos64 = 0;
		byte prevByte = 0;
		while (outSize < 0 || nowPos64 < outSize)
		{
			int posState = (int)nowPos64 & m_PosStateMask;
			if (m_RangeDecoder.DecodeBit(m_IsMatchDecoders, (state << Base.kNumPosStatesBitsMax) + posState) == 0)
			{
				LiteralDecoder.Decoder2 decoder2 = m_LiteralDecoder.GetDecoder((int)nowPos64, prevByte);
				if (!Base.StateIsCharState(state))
					prevByte = decoder2.DecodeWithMatchByte(m_RangeDecoder, m_OutWindow.GetByte(rep0));
				else
					prevByte = decoder2.DecodeNormal(m_RangeDecoder);
				m_OutWindow.PutByte(prevByte);
				state = Base.StateUpdateChar(state);
				nowPos64++;
			}
			else
			{
				int len;
				if (m_RangeDecoder.DecodeBit(m_IsRepDecoders, state) == 1)
				{
					len = 0;
					if (m_RangeDecoder.DecodeBit(m_IsRepG0Decoders, state) == 0)
					{
						if (m_RangeDecoder.DecodeBit(m_IsRep0LongDecoders, (state << Base.kNumPosStatesBitsMax) + posState) == 0)
						{
							state = Base.StateUpdateShortRep(state);
							len = 1;
						}
					}
					else
					{
						int distance;
						if (m_RangeDecoder.DecodeBit(m_IsRepG1Decoders, state) == 0)
							distance = rep1;
						else
						{
							if (m_RangeDecoder.DecodeBit(m_IsRepG2Decoders, state) == 0)
								distance = rep2;
							else
							{
								distance = rep3;
								rep3 = rep2;
							}
							rep2 = rep1;
						}
						rep1 = rep0;
						rep0 = distance;
					}
					if (len == 0)
					{
						len = m_RepLenDecoder.Decode(m_RangeDecoder, posState) + Base.kMatchMinLen;
						state = Base.StateUpdateRep(state);
					}
				}
				else
				{
					rep3 = rep2;
					rep2 = rep1;
					rep1 = rep0;
					len = Base.kMatchMinLen + m_LenDecoder.Decode(m_RangeDecoder, posState);
					state = Base.StateUpdateMatch(state);
					int posSlot = m_PosSlotDecoder[Base.GetLenToPosState(len)].Decode(m_RangeDecoder);
					if (posSlot >= Base.kStartPosModelIndex)
					{
						int numDirectBits = (posSlot >> 1) - 1;
						rep0 = ((2 | (posSlot & 1)) << numDirectBits);
						if (posSlot < Base.kEndPosModelIndex)
							rep0 += BitTreeDecoder.ReverseDecode(m_PosDecoders,
									rep0 - posSlot - 1, m_RangeDecoder, numDirectBits);
						else
						{
							rep0 += (m_RangeDecoder.DecodeDirectBits(
									numDirectBits - Base.kNumAlignBits) << Base.kNumAlignBits);
							rep0 += m_PosAlignDecoder.ReverseDecode(m_RangeDecoder);
							if (rep0 < 0)
							{
								if (rep0 == -1)
									break;
								return false;
							}
						}
					}
					else
						rep0 = posSlot;
				}
				if (rep0 >= nowPos64 || rep0 >= m_DictionarySizeCheck)
				{
					// m_OutWindow.Flush();
					return false;
				}
				m_OutWindow.CopyBlock(rep0, len);
				nowPos64 += len;
				prevByte = m_OutWindow.GetByte(0);
			}
		}
		m_OutWindow.Flush();
		m_OutWindow.ReleaseStream();
		m_RangeDecoder.ReleaseStream();
		return true;
	}
	
	public boolean SetDecoderProperties(byte[] properties)
	{
		if (properties.length < 5)
			return false;
		int val = properties[0] & 0xFF;
		int lc = val % 9;
		int remainder = val / 9;
		int lp = remainder % 5;
		int pb = remainder / 5;
		int dictionarySize = 0;
		for (int i = 0; i < 4; i++)
			dictionarySize += ((int)(properties[1 + i]) & 0xFF) << (i * 8);
		if (!SetLcLpPb(lc, lp, pb))
			return false;
		return SetDictionarySize(dictionarySize);
	}
}

class Base
{
	public static final int kNumRepDistances = 4;
	public static final int kNumStates = 12;
	
	public static final int StateInit()
	{
		return 0;
	}
	
	public static final int StateUpdateChar(int index)
	{
		if (index < 4) 
			return 0;
		if (index < 10) 
			return index - 3;
		return index - 6;
	}
	
	public static final int StateUpdateMatch(int index)
	{
		return (index < 7 ? 7 : 10); 
	}

	public static final int StateUpdateRep(int index)
	{ 
		return (index < 7 ? 8 : 11); 
	}
	
	public static final int StateUpdateShortRep(int index)
	{ 
		return (index < 7 ? 9 : 11); 
	}

	public static final boolean StateIsCharState(int index)
	{ 
		return index < 7; 
	}
	
	public static final int kNumPosSlotBits = 6;
	public static final int kDicLogSizeMin = 0;
	// public static final int kDicLogSizeMax = 28;
	// public static final int kDistTableSizeMax = kDicLogSizeMax * 2;
	
	public static final int kNumLenToPosStatesBits = 2; // it's for speed optimization
	public static final int kNumLenToPosStates = 1 << kNumLenToPosStatesBits;
	
	public static final int kMatchMinLen = 2;
	
	public static final int GetLenToPosState(int len)
	{
		len -= kMatchMinLen;
		if (len < kNumLenToPosStates)
			return len;
		return (int)(kNumLenToPosStates - 1);
	}
	
	public static final int kNumAlignBits = 4;
	public static final int kAlignTableSize = 1 << kNumAlignBits;
	public static final int kAlignMask = (kAlignTableSize - 1);
	
	public static final int kStartPosModelIndex = 4;
	public static final int kEndPosModelIndex = 14;
	public static final int kNumPosModels = kEndPosModelIndex - kStartPosModelIndex;
	
	public static final  int kNumFullDistances = 1 << (kEndPosModelIndex / 2);
	
	public static final  int kNumLitPosStatesBitsEncodingMax = 4;
	public static final  int kNumLitContextBitsMax = 8;
	
	public static final  int kNumPosStatesBitsMax = 4;
	public static final  int kNumPosStatesMax = (1 << kNumPosStatesBitsMax);
	public static final  int kNumPosStatesBitsEncodingMax = 4;
	public static final  int kNumPosStatesEncodingMax = (1 << kNumPosStatesBitsEncodingMax);
	
	public static final  int kNumLowLenBits = 3;
	public static final  int kNumMidLenBits = 3;
	public static final  int kNumHighLenBits = 8;
	public static final  int kNumLowLenSymbols = 1 << kNumLowLenBits;
	public static final  int kNumMidLenSymbols = 1 << kNumMidLenBits;
	public static final  int kNumLenSymbols = kNumLowLenSymbols + kNumMidLenSymbols +
			(1 << kNumHighLenBits);
	public static final  int kMatchMaxLen = kMatchMinLen + kNumLenSymbols - 1;
}

class RangeDecoder
{
	static final int kTopMask = ~((1 << 24) - 1);
	
	static final int kNumBitModelTotalBits = 11;
	static final int kBitModelTotal = (1 << kNumBitModelTotalBits);
	static final int kNumMoveBits = 5;
	
	int Range;
	int Code;

	java.io.InputStream Stream;
	
	public final void SetStream(java.io.InputStream stream)
	{ 
		Stream = stream; 
	}
	
	public final void ReleaseStream()
	{ 
		Stream = null; 
	}
	
	public final void Init() throws IOException
	{
		Code = 0;
		Range = -1;
		for (int i = 0; i < 5; i++)
			Code = (Code << 8) | Stream.read();
	}
	
	public final int DecodeDirectBits(int numTotalBits) throws IOException
	{
		int result = 0;
		for (int i = numTotalBits; i != 0; i--)
		{
			Range >>>= 1;
			int t = ((Code - Range) >>> 31);
			Code -= Range & (t - 1);
			result = (result << 1) | (1 - t);
			
			if ((Range & kTopMask) == 0)
			{
				Code = (Code << 8) | Stream.read();
				Range <<= 8;
			}
		}
		return result;
	}
	
	public int DecodeBit(short []probs, int index) throws IOException
	{
		int prob = probs[index];
		int newBound = (Range >>> kNumBitModelTotalBits) * prob;
		if ((Code ^ 0x80000000) < (newBound ^ 0x80000000))
		{
			Range = newBound;
			probs[index] = (short)(prob + ((kBitModelTotal - prob) >>> kNumMoveBits));
			if ((Range & kTopMask) == 0)
			{
				Code = (Code << 8) | Stream.read();
				Range <<= 8;
			}
			return 0;
		}
		else
		{
			Range -= newBound;
			Code -= newBound;
			probs[index] = (short)(prob - ((prob) >>> kNumMoveBits));
			if ((Range & kTopMask) == 0)
			{
				Code = (Code << 8) | Stream.read();
				Range <<= 8;
			}
			return 1;
		}
	}
	
	public static void InitBitModels(short []probs)
	{
		for (int i = 0; i < probs.length; i++)
			probs[i] = (kBitModelTotal >>> 1);
	}
}

class BitTreeDecoder
{
	short[] Models;
	int NumBitLevels;
	
	public BitTreeDecoder(int numBitLevels)
	{
		NumBitLevels = numBitLevels;
		Models = new short[1 << numBitLevels];
	}
	
	public void Init()
	{
		RangeDecoder.InitBitModels(Models);
	}
	
	public int Decode(RangeDecoder rangeDecoder) throws java.io.IOException
	{
		int m = 1;
		for (int bitIndex = NumBitLevels; bitIndex != 0; bitIndex--)
			m = (m << 1) + rangeDecoder.DecodeBit(Models, m);
		return m - (1 << NumBitLevels);
	}
	
	public int ReverseDecode(RangeDecoder rangeDecoder) throws java.io.IOException
	{
		int m = 1;
		int symbol = 0;
		for (int bitIndex = 0; bitIndex < NumBitLevels; bitIndex++)
		{
			int bit = rangeDecoder.DecodeBit(Models, m);
			m <<= 1;
			m += bit;
			symbol |= (bit << bitIndex);
		}
		return symbol;
	}
	
	public static int ReverseDecode(short[] Models, int startIndex,
			RangeDecoder rangeDecoder, int NumBitLevels) throws java.io.IOException
	{
		int m = 1;
		int symbol = 0;
		for (int bitIndex = 0; bitIndex < NumBitLevels; bitIndex++)
		{
			int bit = rangeDecoder.DecodeBit(Models, startIndex + m);
			m <<= 1;
			m += bit;
			symbol |= (bit << bitIndex);
		}
		return symbol;
	}
}

class BinTree extends InWindow
{
	int _cyclicBufferPos;
	int _cyclicBufferSize = 0;
	int _matchMaxLen;
	
	int[] _son;
	int[] _hash;
	
	int _cutValue = 0xFF;
	int _hashMask;
	int _hashSizeSum = 0;
	
	boolean HASH_ARRAY = true;

	static final int kHash2Size = 1 << 10;
	static final int kHash3Size = 1 << 16;
	static final int kBT2HashSize = 1 << 16;
	static final int kStartMaxLen = 1;
	static final int kHash3Offset = kHash2Size;
	static final int kEmptyHashValue = 0;
	static final int kMaxValForNormalize = (1 << 30) - 1;
	
	int kNumHashDirectBytes = 0;
	int kMinMatchCheck = 4;
	int kFixHashSize = kHash2Size + kHash3Size;

	public void SetType(int numHashBytes)
	{
		HASH_ARRAY = (numHashBytes > 2);
		if (HASH_ARRAY)
		{
			kNumHashDirectBytes = 0;
			kMinMatchCheck = 4;
			kFixHashSize = kHash2Size + kHash3Size;
		}
		else
		{
			kNumHashDirectBytes = 2;
			kMinMatchCheck = 2 + 1;
			kFixHashSize = 0;
		}
	}
	

	

	public void Init() throws IOException
	{
		super.Init();
		for (int i = 0; i < _hashSizeSum; i++)
			_hash[i] = kEmptyHashValue;
		_cyclicBufferPos = 0;
		ReduceOffsets(-1);
	}
	
	public void MovePos() throws IOException
	{
		if (++_cyclicBufferPos >= _cyclicBufferSize)
			_cyclicBufferPos = 0;
		super.MovePos();
		if (_pos == kMaxValForNormalize)
			Normalize();
	}
	

	
	
	
	
	
	
	public boolean Create(int historySize, int keepAddBufferBefore,
			int matchMaxLen, int keepAddBufferAfter)
	{
		if (historySize > kMaxValForNormalize - 256)
			return false;
		_cutValue = 16 + (matchMaxLen >> 1);

		int windowReservSize = (historySize + keepAddBufferBefore +
				matchMaxLen + keepAddBufferAfter) / 2 + 256;
		
		super.Create(historySize + keepAddBufferBefore, matchMaxLen + keepAddBufferAfter, windowReservSize);
		
		_matchMaxLen = matchMaxLen;

		int cyclicBufferSize = historySize + 1;
		if (_cyclicBufferSize != cyclicBufferSize)
			_son = new int[(_cyclicBufferSize = cyclicBufferSize) * 2];

		int hs = kBT2HashSize;

		if (HASH_ARRAY)
		{
			hs = historySize - 1;
			hs |= (hs >> 1);
			hs |= (hs >> 2);
			hs |= (hs >> 4);
			hs |= (hs >> 8);
			hs >>= 1;
			hs |= 0xFFFF;
			if (hs > (1 << 24))
				hs >>= 1;
			_hashMask = hs;
			hs++;
			hs += kFixHashSize;
		}
		if (hs != _hashSizeSum)
			_hash = new int [_hashSizeSum = hs];
		return true;
	}
	public int GetMatches(int[] distances) throws IOException
	{
		int lenLimit;
		if (_pos + _matchMaxLen <= _streamPos)
			lenLimit = _matchMaxLen;
		else
		{
			lenLimit = _streamPos - _pos;
			if (lenLimit < kMinMatchCheck)
			{
				MovePos();
				return 0;
			}
		}

		int offset = 0;
		int matchMinPos = (_pos > _cyclicBufferSize) ? (_pos - _cyclicBufferSize) : 0;
		int cur = _bufferOffset + _pos;
		int maxLen = kStartMaxLen; // to avoid items for len < hashSize;
		int hashValue, hash2Value = 0, hash3Value = 0;
		
		if (HASH_ARRAY)
		{
			int temp = CrcTable[_bufferBase[cur] & 0xFF] ^ (_bufferBase[cur + 1] & 0xFF);
			hash2Value = temp & (kHash2Size - 1);
			temp ^= ((int)(_bufferBase[cur + 2] & 0xFF) << 8);
			hash3Value = temp & (kHash3Size - 1);
			hashValue = (temp ^ (CrcTable[_bufferBase[cur + 3] & 0xFF] << 5)) & _hashMask;
		}
		else
			hashValue = ((_bufferBase[cur] & 0xFF) ^ ((int)(_bufferBase[cur + 1] & 0xFF) << 8));

		int curMatch = _hash[kFixHashSize + hashValue];
		if (HASH_ARRAY)
		{
			int curMatch2 = _hash[hash2Value];
			int curMatch3 = _hash[kHash3Offset + hash3Value];
			_hash[hash2Value] = _pos;
			_hash[kHash3Offset + hash3Value] = _pos;
			if (curMatch2 > matchMinPos)
				if (_bufferBase[_bufferOffset + curMatch2] == _bufferBase[cur])
				{
					distances[offset++] = maxLen = 2;
					distances[offset++] = _pos - curMatch2 - 1;
				}
			if (curMatch3 > matchMinPos)
				if (_bufferBase[_bufferOffset + curMatch3] == _bufferBase[cur])
				{
					if (curMatch3 == curMatch2)
						offset -= 2;
					distances[offset++] = maxLen = 3;
					distances[offset++] = _pos - curMatch3 - 1;
					curMatch2 = curMatch3;
				}
			if (offset != 0 && curMatch2 == curMatch)
			{
				offset -= 2;
				maxLen = kStartMaxLen;
			}
		}

		_hash[kFixHashSize + hashValue] = _pos;

		int ptr0 = (_cyclicBufferPos << 1) + 1;
		int ptr1 = (_cyclicBufferPos << 1);

		int len0, len1;
		len0 = len1 = kNumHashDirectBytes;

		if (kNumHashDirectBytes != 0)
		{
			if (curMatch > matchMinPos)
			{
				if (_bufferBase[_bufferOffset + curMatch + kNumHashDirectBytes] !=
						_bufferBase[cur + kNumHashDirectBytes])
				{
					distances[offset++] = maxLen = kNumHashDirectBytes;
					distances[offset++] = _pos - curMatch - 1;
				}
			}
		}

		int count = _cutValue;

		while (true)
		{
			if (curMatch <= matchMinPos || count-- == 0)
			{
				_son[ptr0] = _son[ptr1] = kEmptyHashValue;
				break;
			}
			int delta = _pos - curMatch;
			int cyclicPos = ((delta <= _cyclicBufferPos) ?
				(_cyclicBufferPos - delta) :
				(_cyclicBufferPos - delta + _cyclicBufferSize)) << 1;

			int pby1 = _bufferOffset + curMatch;
			int len = Math.min(len0, len1);
			if (_bufferBase[pby1 + len] == _bufferBase[cur + len])
			{
				while(++len != lenLimit)
					if (_bufferBase[pby1 + len] != _bufferBase[cur + len])
						break;
				if (maxLen < len)
				{
					distances[offset++] = maxLen = len;
					distances[offset++] = delta - 1;
					if (len == lenLimit)
					{
						_son[ptr1] = _son[cyclicPos];
						_son[ptr0] = _son[cyclicPos + 1];
						break;
					}
				}
			}
			if ((_bufferBase[pby1 + len] & 0xFF) < (_bufferBase[cur + len] & 0xFF))
			{
				_son[ptr1] = curMatch;
				ptr1 = cyclicPos + 1;
				curMatch = _son[ptr1];
				len1 = len;
			}
			else
			{
				_son[ptr0] = curMatch;
				ptr0 = cyclicPos;
				curMatch = _son[ptr0];
				len0 = len;
			}
		}
		MovePos();
		return offset;
	}

	public void Skip(int num) throws IOException
	{
		do
		{
			int lenLimit;
			if (_pos + _matchMaxLen <= _streamPos)
			lenLimit = _matchMaxLen;
			else
			{
				lenLimit = _streamPos - _pos;
				if (lenLimit < kMinMatchCheck)
				{
					MovePos();
					continue;
				}
			}

			int matchMinPos = (_pos > _cyclicBufferSize) ? (_pos - _cyclicBufferSize) : 0;
			int cur = _bufferOffset + _pos;
			
			int hashValue;

			if (HASH_ARRAY)
			{
				int temp = CrcTable[_bufferBase[cur] & 0xFF] ^ (_bufferBase[cur + 1] & 0xFF);
				int hash2Value = temp & (kHash2Size - 1);
				_hash[hash2Value] = _pos;
				temp ^= ((int)(_bufferBase[cur + 2] & 0xFF) << 8);
				int hash3Value = temp & (kHash3Size - 1);
				_hash[kHash3Offset + hash3Value] = _pos;
				hashValue = (temp ^ (CrcTable[_bufferBase[cur + 3] & 0xFF] << 5)) & _hashMask;
			}
			else
				hashValue = ((_bufferBase[cur] & 0xFF) ^ ((int)(_bufferBase[cur + 1] & 0xFF) << 8));

			int curMatch = _hash[kFixHashSize + hashValue];
			_hash[kFixHashSize + hashValue] = _pos;

			int ptr0 = (_cyclicBufferPos << 1) + 1;
			int ptr1 = (_cyclicBufferPos << 1);

			int len0, len1;
			len0 = len1 = kNumHashDirectBytes;

			int count = _cutValue;
			while (true)
			{
				if (curMatch <= matchMinPos || count-- == 0)
				{
					_son[ptr0] = _son[ptr1] = kEmptyHashValue;
					break;
				}

				int delta = _pos - curMatch;
				int cyclicPos = ((delta <= _cyclicBufferPos) ?
					(_cyclicBufferPos - delta) :
					(_cyclicBufferPos - delta + _cyclicBufferSize)) << 1;

				int pby1 = _bufferOffset + curMatch;
				int len = Math.min(len0, len1);
				if (_bufferBase[pby1 + len] == _bufferBase[cur + len])
				{
					while (++len != lenLimit)
						if (_bufferBase[pby1 + len] != _bufferBase[cur + len])
							break;
					if (len == lenLimit)
					{
						_son[ptr1] = _son[cyclicPos];
						_son[ptr0] = _son[cyclicPos + 1];
						break;
					}
				}
				if ((_bufferBase[pby1 + len] & 0xFF) < (_bufferBase[cur + len] & 0xFF))
				{
					_son[ptr1] = curMatch;
					ptr1 = cyclicPos + 1;
					curMatch = _son[ptr1];
					len1 = len;
				}
				else
				{
					_son[ptr0] = curMatch;
					ptr0 = cyclicPos;
					curMatch = _son[ptr0];
					len0 = len;
				}
			}
			MovePos();
		}
		while (--num != 0);
	}
	
	void NormalizeLinks(int[] items, int numItems, int subValue)
	{
		for (int i = 0; i < numItems; i++)
		{
			int value = items[i];
			if (value <= subValue)
				value = kEmptyHashValue;
			else
				value -= subValue;
			items[i] = value;
		}
	}
	
	void Normalize()
	{
		int subValue = _pos - _cyclicBufferSize;
		NormalizeLinks(_son, _cyclicBufferSize * 2, subValue);
		NormalizeLinks(_hash, _hashSizeSum, subValue);
		ReduceOffsets(subValue);
	}
	
	public void SetCutValue(int cutValue) { _cutValue = cutValue; }

	private static final int[] CrcTable = new int[256];

	static
	{
		for (int i = 0; i < 256; i++)
		{
			int r = i;
			for (int j = 0; j < 8; j++)
				if ((r & 1) != 0)
					r = (r >>> 1) ^ 0xEDB88320;
				else
					r >>>= 1;
			CrcTable[i] = r;
		}
	}
}
// LZ.InWindow

class InWindow
{
	public byte[] _bufferBase; // pointer to buffer with data
	java.io.InputStream _stream;
	int _posLimit;  // offset (from _buffer) of first byte when new block reading must be done
	boolean _streamEndWasReached; // if (true) then _streamPos shows real end of stream
	
	int _pointerToLastSafePosition;
	
	public int _bufferOffset;
	
	public int _blockSize;  // Size of Allocated memory block
	public int _pos;             // offset (from _buffer) of curent byte
	int _keepSizeBefore;  // how many BYTEs must be kept in buffer before _pos
	int _keepSizeAfter;   // how many BYTEs must be kept buffer after _pos
	public int _streamPos;   // offset (from _buffer) of first not read byte from Stream
	
	public void MoveBlock()
	{
		int offset = _bufferOffset + _pos - _keepSizeBefore;
		// we need one additional byte, since MovePos moves on 1 byte.
		if (offset > 0)
			offset--;

		int numBytes = _bufferOffset + _streamPos - offset;
		
		// check negative offset ????
		for (int i = 0; i < numBytes; i++)
			_bufferBase[i] = _bufferBase[offset + i];
		_bufferOffset -= offset;
	}
	
	public void ReadBlock() throws IOException
	{
		if (_streamEndWasReached)
			return;
		while (true)
		{
			int size = (0 - _bufferOffset) + _blockSize - _streamPos;
			if (size == 0)
				return;
			int numReadBytes = _stream.read(_bufferBase, _bufferOffset + _streamPos, size);
			if (numReadBytes == -1)
			{
				_posLimit = _streamPos;
				int pointerToPostion = _bufferOffset + _posLimit;
				if (pointerToPostion > _pointerToLastSafePosition)
					_posLimit = _pointerToLastSafePosition - _bufferOffset;
				
				_streamEndWasReached = true;
				return;
			}
			_streamPos += numReadBytes;
			if (_streamPos >= _pos + _keepSizeAfter)
				_posLimit = _streamPos - _keepSizeAfter;
		}
	}
	
	void Free() { _bufferBase = null; }
	
	public void Create(int keepSizeBefore, int keepSizeAfter, int keepSizeReserv)
	{
		_keepSizeBefore = keepSizeBefore;
		_keepSizeAfter = keepSizeAfter;
		int blockSize = keepSizeBefore + keepSizeAfter + keepSizeReserv;
		if (_bufferBase == null || _blockSize != blockSize)
		{
			Free();
			_blockSize = blockSize;
			_bufferBase = new byte[_blockSize];
		}
		_pointerToLastSafePosition = _blockSize - keepSizeAfter;
	}
	
	public void SetStream(java.io.InputStream stream) { _stream = stream; 	}
	public void ReleaseStream() { _stream = null; }

	public void Init() throws IOException
	{
		_bufferOffset = 0;
		_pos = 0;
		_streamPos = 0;
		_streamEndWasReached = false;
		ReadBlock();
	}
	
	public void MovePos() throws IOException
	{
		_pos++;
		if (_pos > _posLimit)
		{
			int pointerToPostion = _bufferOffset + _pos;
			if (pointerToPostion > _pointerToLastSafePosition)
				MoveBlock();
			ReadBlock();
		}
	}
	
	public byte GetIndexByte(int index)	{ return _bufferBase[_bufferOffset + _pos + index]; }
	
	// index + limit have not to exceed _keepSizeAfter;
	public int GetMatchLen(int index, int distance, int limit)
	{
		if (_streamEndWasReached)
			if ((_pos + index) + limit > _streamPos)
				limit = _streamPos - (_pos + index);
		distance++;
		// Byte *pby = _buffer + (size_t)_pos + index;
		int pby = _bufferOffset + _pos + index;
		
		int i;
		for (i = 0; i < limit && _bufferBase[pby + i] == _bufferBase[pby + i - distance]; i++);
		return i;
	}
	
	public int GetNumAvailableBytes()	{ return _streamPos - _pos; }
	
	public void ReduceOffsets(int subValue)
	{
		_bufferOffset += subValue;
		_posLimit -= subValue;
		_pos -= subValue;
		_streamPos -= subValue;
	}
}
class OutWindow
{
	byte[] _buffer;
	int _pos;
	int _windowSize = 0;
	int _streamPos;
	java.io.OutputStream _stream;
	
	public void Create(int windowSize)
	{
		if (_buffer == null || _windowSize != windowSize)
			_buffer = new byte[windowSize];
		_windowSize = windowSize;
		_pos = 0;
		_streamPos = 0;
	}
	
	public void SetStream(java.io.OutputStream stream) throws IOException
	{
		ReleaseStream();
		_stream = stream;
	}
	
	public void ReleaseStream() throws IOException
	{
		Flush();
		_stream = null;
	}
	
	public void Init(boolean solid)
	{
		if (!solid)
		{
			_streamPos = 0;
			_pos = 0;
		}
	}
	
	public void Flush() throws IOException
	{
		int size = _pos - _streamPos;
		if (size == 0)
			return;
		_stream.write(_buffer, _streamPos, size);
		if (_pos >= _windowSize)
			_pos = 0;
		_streamPos = _pos;
	}
	
	public void CopyBlock(int distance, int len) throws IOException
	{
		int pos = _pos - distance - 1;
		if (pos < 0)
			pos += _windowSize;
		for (; len != 0; len--)
		{
			if (pos >= _windowSize)
				pos = 0;
			_buffer[_pos++] = _buffer[pos++];
			if (_pos >= _windowSize)
				Flush();
		}
	}
	
	public void PutByte(byte b) throws IOException
	{
		_buffer[_pos++] = b;
		if (_pos >= _windowSize)
			Flush();
	}
	
	public byte GetByte(int distance)
	{
		int pos = _pos - distance - 1;
		if (pos < 0)
			pos += _windowSize;
		return _buffer[pos];
	}
}
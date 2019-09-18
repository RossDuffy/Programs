public class AStar
{
	public static void main(String args[])
	{
		int[][] startState = new int[3][3];
		int[][] endState = new int[3][3];
		int init = 1;
		for(int i = 0; i < endState.length - 1; i++)
		{
			for (int j = 0; j < endState[i].length; j++)
			{
				endState[i][j] = init;
				init++;
			}
		}
		endState[3][3] = 0;

		int heuristic = heuristic(startState, endState);
	}

	//best-first-search
	//tiles-out-of-place heuristic

	/*
	* takes the current state and end state;
	* compares both, counting how many tiles are out of place;
	* returns that count as the heuristic value
	* */
	public static int heuristic(int [][] inState, int[][] endState)
	{
		int heuristic = 0;
		for(int o = 0; o < inState.length; o++)
		{
			for(int p = 0; p < inState[o].length; p++)
			{
				if(inState[o][p] != endState[o][p])
					heuristic++;
			}
		}
		return heuristic;
	}
}

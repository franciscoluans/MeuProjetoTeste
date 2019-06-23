package aima.gui.demo.search;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.*;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensFunctions;
import aima.core.environment.nqueens.QueenAction;
import aima.core.environment.nqueens.NQueensBoard.Config;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.informed.RecursiveBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.*;
import aima.core.search.informed.*;

import java.util.List;
import java.util.Properties;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * 
 */

public class EightPuzzleDemo2 {
	private static EightPuzzleBoard boardWithThreeMoveSolution =
			new EightPuzzleBoard(new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });

	private static EightPuzzleBoard random1 =
			new EightPuzzleBoard(new int[] { 1, 4, 2, 7, 5, 8, 3, 0, 6 });

//	private static EightPuzzleBoard extreme =
//			new EightPuzzleBoard(new int[] { 0, 8, 7, 6, 5, 4, 3, 2, 1 });
	private static final  boolean  t = true;
	public static void main(String[] args) {
		//eightPuzzleDemoRecursiveBestFirstSearch();
		//eightPuzzleDemoUniformCostSearch();
	//	eightPuzzleDemoDepthFirstSearch();
		//eightPuzzleDemoBreadthFirstSearch();
		eightPuzzleDemoBestFirstSearch();
		eightPuzzleDemoBestFirstSearch2();
		eightPuzzleDemoBestFirstSearch3();
		//eightPuzzleDemoBestFirstSearch4();//erro de memoria
		//eightPuzzleDLSDemo();
		/*eightPuzzleDLSDemo();
		eightPuzzleIDLSDemo();
		eightPuzzleGreedyBestFirstDemo();
		eightPuzzleGreedyBestFirstManhattanDemo();
		eightPuzzleAStarDemo();
		eightPuzzleAStarManhattanDemo();
		eightPuzzleSimulatedAnnealingDemo();*/
	}

	private static void eightPuzzleDLSDemo() {
		System.out.println("\nEightPuzzleDemo recursive DLS (9) -->");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new DepthLimitedSearch<>(9);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleIDLSDemo() {
		System.out.println("\nEightPuzzleDemo Iterative DLS -->");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new IterativeDeepeningSearch<>();
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//usando peças fora do lugar
	private static void eightPuzzleGreedyBestFirstDemo() {
		System.out.println("\nEightPuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)-->");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions.createMisplacedTileHeuristicFunction());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//usando distancia de manhattan
	private static void eightPuzzleGreedyBestFirstManhattanDemo() {
		System.out.println("\nEightPuzzleDemo Greedy Best First Search (ManhattanHeursitic)-->");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions.createManhattanHeuristicFunction());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// distancia de manhattan
		private static void eightPuzzleAStarManhattanDemo() {
			System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)-->");
			try {
				Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
				SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
						(new GraphSearch<>(), EightPuzzleFunctions.createManhattanHeuristicFunction());
				SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
				printActions(agent.getActions());
				printInstrumentation(agent.getInstrumentation());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
//usando peças fora do lugar
	private static void eightPuzzleAStarDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)-->");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions.createMisplacedTileHeuristicFunction());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//usando distancia de manhattan
	private static void eightPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nEightPuzzleDemo Simulated Annealing  Search -->");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SimulatedAnnealingSearch<EightPuzzleBoard, Action> search = new SimulatedAnnealingSearch<>
					(EightPuzzleFunctions.createManhattanHeuristicFunction());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//usando peças fora do lugar
		private static void eightPuzzleSimulatedAnnealingDemo2() {
			System.out.println("\nEightPuzzleDemo Simulated Annealing  Search -->");
			try {
				Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
				SimulatedAnnealingSearch<EightPuzzleBoard, Action> search = new SimulatedAnnealingSearch<>
						(EightPuzzleFunctions.createMisplacedTileHeuristicFunction());
				SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
				printActions(agent.getActions());
				System.out.println("Search Outcome=" + search.getOutcome());
				System.out.println("Final State=\n" + search.getLastSearchState());
				printInstrumentation(agent.getInstrumentation());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// bread first search 
		private static void eightPuzzleDemoBreadthFirstSearch() {
		try {
			System.out.println("\nEightPuzzleDemo BFS -->");
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new BreadthFirstSearch<>(new TreeSearch<>());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		//best first search used manhattan heuristc 
		private static void eightPuzzleDemoBestFirstSearch() {
		try {
			System.out.println("\nEightPuzzleDemo BFS -->");
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			//SearchForActions<EightPuzzleBoard, Action> search = new BreadthFirstSearch<>(new TreeSearch<>());
			SearchForActions<EightPuzzleBoard, Action> search = new BestFirstSearch<>
			(new GraphSearch<>(), EightPuzzleFunctions.createManhattanHeuristicFunction());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		//best first search used manhattan heuristc 
		private static void eightPuzzleDemoBestFirstSearch2() {
		try {
			System.out.println("\nEightPuzzleDemo BFS -->");
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			//SearchForActions<EightPuzzleBoard, Action> search = new BreadthFirstSearch<>(new TreeSearch<>());
			SearchForActions<EightPuzzleBoard, Action> search = new BestFirstSearch<>
			(new TreeSearch<>(), EightPuzzleFunctions.createManhattanHeuristicFunction());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		//best first search used misplaced heuristc 
		private static void eightPuzzleDemoBestFirstSearch3() {
		try {
			System.out.println("\nEightPuzzleDemo BFS -->");
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			//SearchForActions<EightPuzzleBoard, Action> search = new BreadthFirstSearch<>(new TreeSearch<>());
			SearchForActions<EightPuzzleBoard, Action> search = new BestFirstSearch<>
			(new GraphSearch<>(),EightPuzzleFunctions.createMisplacedTileHeuristicFunction());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		//best first search used misplaced heuristc 
				private static void eightPuzzleDemoBestFirstSearch4() {
				try {
					System.out.println("\nEightPuzzleDemo BFS -->");
					Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
					//SearchForActions<EightPuzzleBoard, Action> search = new BreadthFirstSearch<>(new TreeSearch<>());
					SearchForActions<EightPuzzleBoard, Action> search = new BestFirstSearch<>
					(new TreeSearch<>(),EightPuzzleFunctions.createMisplacedTileHeuristicFunction());
					SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
					printActions(agent.getActions());
					printInstrumentation(agent.getInstrumentation());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		//depth first search
		private static void eightPuzzleDemoDepthFirstSearch() {
			System.out.println("\neightPuzzleDemo DFS -->");
			try {
				Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
				SearchForActions<EightPuzzleBoard, Action> search = new DepthFirstSearch<>(new GraphSearch<>());
				SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
				printActions(agent.getActions());
				printInstrumentation(agent.getInstrumentation());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// busca de custo uniforme
		private static void eightPuzzleDemoUniformCostSearch() {
			System.out.println("\nNQueensDemos UCS --> ");
			try {
				Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
				SearchForActions<EightPuzzleBoard, Action> search = new UniformCostSearch<>(new GraphSearch());
				SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
				System.out.println();
				printActions(agent.getActions());
				printInstrumentation(agent.getInstrumentation());
			} catch(Exception e) {
				e.printStackTrace();
			};
			
		}
		//busca recursiva de melhor escolha manhattan
		
		private static void eightPuzzleDemoRecursiveBestFirstSearch() {
			System.out.println("\n eightPuzzleDemo recursive Best First Search -->");
			try {
				Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
				SearchForActions<EightPuzzleBoard, Action> search = new RecursiveBestFirstSearch<>( EightPuzzleFunctions.createManhattanHeuristicFunction(),t );
				SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
				printActions(agent.getActions());
				printInstrumentation(agent.getInstrumentation());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//busca recursiva de melhor escolha peças fora do  lugar
		private static void eightPuzzleDemoRecursiveBestFirstSearch2() {
			System.out.println("\n eightPuzzleDemo recursive Best First Search -->");
			try {
				Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
				SearchForActions<EightPuzzleBoard, Action> search = new RecursiveBestFirstSearch<>( EightPuzzleFunctions.createMisplacedTileHeuristicFunction(),t );
				SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
				printActions(agent.getActions());
				printInstrumentation(agent.getInstrumentation());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	private static void printInstrumentation(Properties properties) {
		for (Object o : properties.keySet()) {
			String key = (String) o;
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List<Action> actions) {
		actions.forEach(System.out::println);
	}
}
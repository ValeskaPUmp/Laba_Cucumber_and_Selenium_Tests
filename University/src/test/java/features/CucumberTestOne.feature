# language:en
@all
  Feature: CucumberJava
    Scenario:Test DFS
      Given Tree was  created and added items
        | NaUKMA         |
        | FI             |
        | Student1       |
        | Student2       |
        | Student3       |
        | Student5       |
      When Amount of graphs in each graph in the tree equal 1
      Then DFS is working correctly
    Scenario:Test ResetAll
      Given Tree was  created and added objects
        | NaUKMA         |
        | FI             |
        | Student1       |
        | Student2       |
        | Student3       |
        | Student5       |
      When Using a method ResetAll
      Then All graphs become unvisited
    Scenario:Test ClearAllBranchFrom
      Given Tree was  created and added graphs
        | NaUKMA         |
        | FI             |
        | Student1       |
        | Student2       |
        | Student3       |
        | Student5       |
      When Using a method clearAllBranchFrom
      Then All graphs that was linked with root,have deleted




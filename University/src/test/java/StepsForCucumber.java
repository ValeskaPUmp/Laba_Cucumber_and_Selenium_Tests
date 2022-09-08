import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.example.TreeUniversity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;
public class StepsForCucumber {
    TreeUniversity treeUniversity=SingletoneForSteps.getInstance();
    TreeUniversity.Node firstNode;

    @Given("^Tree was  created and added items$")
    public void tree_was_created_and_added_items(List<String> arg1) throws Throwable {
        if(firstNode==null){
            firstNode=new TreeUniversity.Node(arg1.get(0));
            treeUniversity.addEdge(firstNode,new TreeUniversity.Node(arg1.get(1)));
        }
        for(int i=1;i<arg1.size()-2;++i){
            treeUniversity.addEdge(new TreeUniversity.Node(arg1.get(i)),new TreeUniversity.Node(arg1.get(i+1)));
        }


    }

    @When("^Amount of graphs in each graph in the tree equal (\\d+)$")
    public void amount_of_graphs_in_the_tree_equal(int arg1) throws Throwable {
        treeUniversity.DFS(firstNode);
        Assertions.assertEquals(treeUniversity.resultDFS.size(),arg1);
    }

    @Then("^DFS is working correctly$")
    public void dfs_is_working_correctly() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("DFS is working correctly");
    }

    @Given("^Tree was  created and added objects$")
    public void tree_was_created_and_added_objects(List<String> arg1) throws Throwable {
        if(firstNode==null){
            firstNode=new TreeUniversity.Node(arg1.get(0));
            treeUniversity.addEdge(firstNode,new TreeUniversity.Node(arg1.get(1)));
        }
        for(int i=1;i<arg1.size()-2;++i){
            treeUniversity.addEdge(new TreeUniversity.Node(arg1.get(i)),new TreeUniversity.Node(arg1.get(i+1)));
        }
    }

    @When("^Using a method ResetAll$")
    public void using_a_method_ResetAll() throws Throwable {
        treeUniversity.resetAll();
    }

    @Then("^All graphs become unvisited$")
    public void all_graphs_become_unvisited() throws Throwable {
        ArrayList<Boolean> booleans=new ArrayList<>();
        for(TreeUniversity.Node n:treeUniversity.framework.keySet()){
            booleans.add(n.visited);
        }
        Assertions.assertTrue(!booleans.contains(true));
    }

    @Given("^Tree was  created and added graphs$")
    public void tree_was_created_and_added_graphs(List<String> arg1) throws Throwable {
        if(firstNode==null){
            firstNode=new TreeUniversity.Node(arg1.get(0));
            treeUniversity.addEdge(firstNode,new TreeUniversity.Node(arg1.get(1)));
        }
        for(int i=1;i<arg1.size()-2;++i){
            treeUniversity.addEdge(new TreeUniversity.Node(arg1.get(i)),new TreeUniversity.Node(arg1.get(i+1)));
        }
    }

    @When("^Using a method clearAllBranchFrom$")
    public void using_a_method_clearAllBranchFrom() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        treeUniversity.clearAllBranchFrom(firstNode);
    }

    @Then("^All graphs that was linked with root,have deleted$")
    public void all_graphs_that_was_linked_with_root_have_deleted() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(treeUniversity.framework.keySet().size(),23);
    }


}

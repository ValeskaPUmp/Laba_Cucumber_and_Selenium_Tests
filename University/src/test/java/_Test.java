import org.example.TreeUniversity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

public class _Test
{
    static TreeUniversity treeUniversity;
    static TreeUniversity.Node all=new TreeUniversity.Node("NaUKMA");
    static TreeUniversity.Node faculty1=new TreeUniversity.Node("Fi");
    static TreeUniversity.Node student1=new TreeUniversity.Node("John Makkein");
    static TreeUniversity.Node student2=new TreeUniversity.Node("Paul Vein");
    static TreeUniversity.Node faculty2=new TreeUniversity.Node("FP");
   static  TreeUniversity.Node student3=new TreeUniversity.Node("Klein");
    @BeforeAll
    public static void init(){
        treeUniversity=new TreeUniversity();
        treeUniversity.addEdge(all,faculty1);
        treeUniversity.addEdge(all,faculty2);
        treeUniversity.addEdge(faculty2,student2);
        treeUniversity.addEdge(faculty1,student1);
        treeUniversity.addEdge(faculty2,student3);

    }
    @Test
    @Tag("SampleTests")
    public void testExistFaculty1(){
        Assumptions.assumeTrue(!treeUniversity.framework.isEmpty());
        Assertions.assertTrue(treeUniversity.framework.get(all).contains(faculty1));

    }
    @Test
    @Tag("SampleTests")
    protected void testDFS(){
        Assumptions.assumeTrue(!treeUniversity.framework.isEmpty());
        treeUniversity.DFS(all);
        assertThat(treeUniversity.resultDFS.size()).isBetween(3,6);
        Assertions.assertEquals(treeUniversity.resultDFS.size(),5);
    }
    @Test
    @Tag("SampleTests")
    protected void testResetAll(){
        Assumptions.assumeTrue(!treeUniversity.framework.isEmpty());
        ArrayList<Boolean> booleans=new ArrayList<>();
        treeUniversity.resetAll();
        for(TreeUniversity.Node n:treeUniversity.framework.keySet()){
            booleans.add(n.visited);
        }
        assertThat(!booleans.contains(true)).isTrue();

    }
    @ParameterizedTest
    @ValueSource(strings={"ExampleStudent1","ExampleStudent2","ExampleStudent3"})
    @Tag("SampleTests")
    protected void testGetName(String nameObject){
        Assumptions.assumeTrue(!treeUniversity.framework.isEmpty());
        TreeUniversity.Node tempNode=new TreeUniversity.Node(nameObject);
        treeUniversity.addEdge(all,tempNode);
        Assertions.assertTrue(treeUniversity.framework.containsKey(tempNode));

    }
    @Test
    @Tag("SampleTests")
    protected void clearLastBranchFromSomeNode(){
        treeUniversity.clearAllBranchFrom(faculty2);
        assertThat(treeUniversity.framework.size()).isEqualTo(8);

    }

}

import org.example.TreeUniversity;

public class SingletoneForSteps {
    private static TreeUniversity treeUniversity;
    public static TreeUniversity getInstance(){
        if(treeUniversity==null){
            treeUniversity=new TreeUniversity();
        }
        return  treeUniversity;
    }
}

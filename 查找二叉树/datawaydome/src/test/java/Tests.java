import com.fasterxml.jackson.databind.JsonNode;
import com.xiongs.dataway.model.BinaryTree;
import com.xiongs.dataway.model.HashBinaryTree;
import com.xiongs.dataway.model.HashTreeNode;
import com.xiongs.dataway.model.ReportBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @description:
 * @author: xiong_s on 2020/5/14 17:38
 */
public class Tests {
    @Test
    public void BTreeTest(){
        BinaryTree binaryTree = new BinaryTree();
        int [] data={6,1,9,3,5,4,8,7,2};
        BinaryTree tree = binaryTree.build(data);
        boolean flag = tree.put(10);
        tree.deleted(9);
        System.out.println(tree.select(6).toString());
        System.out.println(tree.check());
        System.out.println(new String("asdasd").hashCode());
        System.out.println(new Long(10).hashCode()>new Integer(9).hashCode());
    }
    @Test
    public void HashBTreeTest(){
        List<String> list = new ArrayList<>();
        list.add("我");
        list.add("很");
        list.add("牛");
        list.add("逼");
        list.add("，");
        list.add("非");
        list.add("常");
        list.add("能");
        list.add("秀");
        HashBinaryTree<String> hashBinaryTree = new HashBinaryTree<>();
        hashBinaryTree.build(list);
        HashTreeNode<String> 牛 = hashBinaryTree.select("牛");
        System.out.println(牛);
    }

    @Test
    public void JsonTest(){
        ReportBean reportBean = new ReportBean();
        reportBean.setValue(111);
        reportBean.setDataType("123");
    }
}

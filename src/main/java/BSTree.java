public class BSTree {
    private Node root;
	class Node
    {
        String data;
        Node left, right;

        public Node(String d)
        {
            data = d;
            right = null;
            left = null;
        }
    }

    public void insert(String data)
    {
         root = insertData(root, data);
    }
    private Node insertData(Node root, String data)
    {
        if (root == null)
        {
            root = new Node(data);
            return root;
        }
        if (data.compareTo(root.data) < 0)
            root.left = insertData(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = insertData(root.right, data);
        return root;
    }
    public boolean search(String data)  { 
        Node root1 = searchData(root, data); 
        if (root1!= null)
            return true;
        else
            return false;
    } 
    private Node searchData(Node root, String data)  { 
        if (root==null || root.data.equals(data)) 
            return root; 
        if (root.data.compareTo(data)>0) 
            return searchData(root.left, data); 
        return searchData(root.right, data); 
    }
    public void inorder() { 
        inorderData(root); 
    } 
     
    private void inorderData(Node root) { 
        if (root != null) { 
            inorderData(root.left); 
            System.out.println(root.data); 
            inorderData(root.right); 
        } 
    }
}

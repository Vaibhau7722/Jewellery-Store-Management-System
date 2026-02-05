package com.example.JewelleryStoreManagementSystem;

import java.io.Serializable;

public class MyList<listItem> implements Serializable {

    public static class Node<node> implements Serializable {
        public Node<node> next = null;
        private node contents;

        //getter
        public node getContent() {
            return contents;
        }

        //setter
        public void setContent(node content) {
            contents = content;
        }
    }

    public Node<listItem> head = null;

    public void addNode(listItem item) {
        Node<listItem> temporaryContents = new Node<>();
        temporaryContents.setContent(item);
        temporaryContents.next = head;
        head = temporaryContents;
    }

    public int getListSize() {
        Node<listItem> node = head;
        int quantity = 0;
        while (node != null) {
            node = node.next;
            quantity++;
        }
        return quantity;
    }

    public void clearList() {
        head = null;
    }

    public Node<listItem> get(int item) {
        Node<listItem> temporaryNode = head;
        int i = 0;
        while(temporaryNode!=null && i<item) {
            temporaryNode = temporaryNode.next;
            i++;
        }
        return temporaryNode;
    }

    public void deleteListItem(int item){
        int i = 0;
        Node<listItem> temporaryNode = head;
        while(temporaryNode!=null && i<item) {
            temporaryNode = temporaryNode.next;
            i++;
        }
        if(i==0) {
            assert head != null;
            head = head.next;
        }
        if (temporaryNode != null && temporaryNode.next != null) {
            temporaryNode.next = temporaryNode.next.next;
        }
    }


}

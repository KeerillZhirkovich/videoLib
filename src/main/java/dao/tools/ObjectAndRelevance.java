package dao.tools;

public class ObjectAndRelevance<T> implements Comparable<ObjectAndRelevance> {

    private T data;
    private int relevance;

    public ObjectAndRelevance(T data) {
        this.data = data;
        relevance = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    @Override
    public int compareTo(ObjectAndRelevance o) {
        return this.relevance - o.relevance;
    }
}

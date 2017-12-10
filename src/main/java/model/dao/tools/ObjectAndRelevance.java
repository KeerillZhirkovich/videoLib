package model.dao.tools;

/**
 * Служебный класс, объединяющий в себе данные и их релевантность.
 */
public class ObjectAndRelevance<T> implements Comparable<ObjectAndRelevance> {

    private T data;
    private int relevance;

    public ObjectAndRelevance(T data) {
        this.data = data;
        relevance = 0;
    }

    /**
     * Метод, возвращающий данные.
     */
    public T getData() {
        return data;
    }

    /**
     * Метод, устанавливающий данные.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Метод, возвращающий релевантность данных.
     */
    public int getRelevance() {
        return relevance;
    }

    /**
     * Метод, устанавливающий релевантность данных.
     */
    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    @Override
    public int compareTo(ObjectAndRelevance o) {
        return o.relevance - this.relevance;
    }
}

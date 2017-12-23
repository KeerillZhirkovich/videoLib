package model.dao.tools;

/**
 * Служебный класс, объединяющий в себе данные и их релевантность.
 */
public class ObjectAndRelevance<T> implements Comparable<ObjectAndRelevance> {

    private T data;
    private int relevance;

    /**
     * @param data
     */
    public ObjectAndRelevance(T data) {
        this.data = data;
        relevance = 0;
    }


    /**
     * Метод, возвращающий данные.
     * @return
     */
    public T getData() {
        return data;
    }


    /**
     * Метод, устанавливающий данные.
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Метод, возвращающий релевантность данных.
     * @return
     */
    public int getRelevance() {
        return relevance;
    }

    /**
     * Метод, устанавливающий релевантность данных.
     * @param relevance
     */
    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public int compareTo(ObjectAndRelevance o) {
        return o.relevance - this.relevance;
    }
}

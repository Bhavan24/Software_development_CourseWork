package CW.Task4;

/**
 * The Hotel program implements an application that
 * does the following operations (with Room & Person & HotelQueue)
 * add/view/delete/find/store/load/viewByOrder
 *
 * @author bhavan
 * @version Task 4 (Class Version)
 */
public class HotelQueue {
    int size;
    int rear;
    int front;
    Room[] circularQueueArray;

    /* constructor to set circular array size
     * front & rear are element po*/
    public HotelQueue() {
        this.size = 10;
        this.circularQueueArray = new Room[size];
        this.rear = -1;
        this.front = -1;
    }

    // add item to queue
    public void enQueue(Room item) {
        if (rear == front && front == -1) {
            front = 0;
        }
        rear = (rear + 1) % size;
        circularQueueArray[rear] = item;
    }

    // remove item from queue and return the removed item
    public Room deQueue() {
        Room room = circularQueueArray[front];
        circularQueueArray[front] = new Room();//reset data
        if (rear == front) {
            rear = -1;
            front = -1;
        } else {
            front = (front + 1) % size;
        }
        return room;
    }

    // check if the queue is empty
    public boolean isEmpty() {
        return (rear == front && rear == -1);
    }
}

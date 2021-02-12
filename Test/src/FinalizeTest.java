
public class FinalizeTest {
    public static void main(String[] args) throws InterruptedException {
        Book book1 = new Book("天龙八部");

        new Book("哆啦a梦");

        System.gc();

        Thread.sleep(2000);
        System.out.println(book1);
    }
}

class Book {

    private boolean checkIn;
    private String name;

    public Book(String name) {
        this.checkIn = true;
        this.name = name;
    }

    public void checkOut() {
        this.checkIn = false;
    }

    /**
     * 最后一道保命机制
     */
    @Override
    protected void finalize() throws Throwable {
        if (this.checkIn) {
            System.out.println(this.name + " not check out yet");
        }
        super.finalize();
    }

}
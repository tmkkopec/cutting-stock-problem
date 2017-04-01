public class Main {

    public static void main(String []args){

    }


    /* TODO Delete this method
    private void sampleUsage(){
        ResourceManager rm = new ResourceManager();

        try {
            List<Picture> list = rm.getPicturesFromResources();

            list.get(0).setStartingPositionX(20);
            list.get(0).setStartingPositionY(30);

            list.get(1).setStartingPositionX(100);
            list.get(1).setStartingPositionY(300);

            Visualiser v = new Visualiser(list);
            v.visualise();


            // Reuse
            Thread.sleep(2000);

            list.get(1).setStartingPositionX(200);
            list.get(1).setStartingPositionY(200);
            v.setPictures(list);

            v.visualise();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    */
}

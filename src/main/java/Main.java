import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        List<TreeNode> continents = new ArrayList<>();

        /*List<String> dataList=ReadExcelFile.ReadExcel("G:\\Jobs Recruitement Task\\AsadTestProject", "WorldMap.xlsx", "Sheet1");*/
        TreeNode world = new TreeNode("World", continents);

        // By reading dataList What I can do afterwards is I will iterate list and insert continents, countries and cities using
        // insertInNode for e.g insertInNode(dataList.get(i), dataList.get(i + 1), dataList.get(i + 2), world)
 /*     for (int i = 0; i <= dataList.size(); i ++) {

          if(dataList.size()>i+2) {
              break;
          }else{
              insertInNode(dataList.get(i), dataList.get(i + 1), dataList.get(i + 2), world);
          }
      }*/
        System.out.println("Done Inserting");
        insertInNode("Asia", "Pakistan", "Karachi", world);
        insertInNode("Asia", "Pakistan", "Karachi", world);
        insertInNode("Asia", "Pakistan", "Lahore", world);
        insertInNode("Asia", "India", "Mumbai", world);
        insertInNode("Asia", "India", "Mumbai", world);
        insertInNode("Asia", "India", "Delhi", world);

        insertInNode("Europe", "Germany", "Frankfurt", world);
        insertInNode("Europe", "France", "Paris", world);
        insertInNode("Europe", "Netherlands", "Amsterdam", world);

        printChild(world, "Pakistan");

        findRelationBetweenParentAndChild(world, "Asia", "Pakistan");
        findRelationBetweenParentAndChild(world, "Asia", "Germany");

        findRelationBetweenParentAndChild(world, "Germany", "Karachi");
        findRelationBetweenParentAndChild(world, "Netherlands", "Amsterdam");

        findRelationBetweenParentAndChild(world, "Karachi", "Lahore");

    }

    private static void findRelationBetweenParentAndChild(TreeNode parentNode, String parentName, String childName) {
        TreeNode continentNode = TreeNode.findNode(parentNode, parentName);

        if(continentNode != null) {
            TreeNode countryNode = TreeNode.findNode(continentNode, childName);
            if(countryNode != null) {
                System.out.println(""+parentName+ " is a parent of "+ childName);
            }
            else {
                System.out.println("Invalid Parent Child relationship for " + parentName + " and "+ childName);
            }
        }
        else {
            boolean noDataFound = true;
            for(TreeNode continent : parentNode.child) {
                TreeNode countryNode = TreeNode.findNode(continent, parentName);
                if(countryNode != null) {
                    TreeNode cityNode = TreeNode.findNode(countryNode, childName);
                    if(cityNode != null) {
                        System.out.println("" + parentName + " is a parent of " + childName);
                        noDataFound = false;
                        break;
                    }
                    else {
                        noDataFound = true;
                    }
                }
                else {
                    noDataFound = true;
                }
            }

            if(noDataFound)
                System.out.println("Invalid Parent Child relationship for " + parentName + " and "+ childName);
        }

    }

    private static void printChild(TreeNode parentNode, String parentName) {

        TreeNode continentNode = TreeNode.findNode(parentNode, parentName);

        if (continentNode != null) {
            for (TreeNode country : continentNode.child) {
                System.out.println(country.name);
            }
        } else {
            boolean noDataFound = true;

            for (TreeNode continents : parentNode.child) {
                TreeNode countryNode = TreeNode.findNode(continents, parentName);

                if (countryNode != null) {
                    for (TreeNode city : countryNode.child) {
                        System.out.println(city.name);
                    }
                    noDataFound = false;
                    break;
                } else {
                    noDataFound = true;
                }
            }

            if (noDataFound) {
                System.out.println("Invalid Parent Name");
            }
        }
    }


    private static void insertInNode(String continent, String country, String city, TreeNode parentNode) {

        if (parentNode.child.isEmpty()) {           //Runs only when first continent is getting inserted
            List<TreeNode> cities = new ArrayList<TreeNode>();
            TreeNode cityNode = new TreeNode(city, null);
            cities.add(cityNode);

            List<TreeNode> countries = new ArrayList<>();
            countries.add(new TreeNode(country, cities));

            TreeNode continentNode = new TreeNode(continent, countries);

            List<TreeNode> continents = new ArrayList<>();
            continents.add(continentNode);

            parentNode.child = continents;
        } else {
            TreeNode continentNode = TreeNode.findNode(parentNode, continent); //checking if the continent already exists
            if (continentNode != null) {
                TreeNode countryNode = TreeNode.findNode(continentNode, country);//checking if the country already exists

                if (countryNode != null) {  //if a new city is getting inserted of same country
                    TreeNode cityNode2 = TreeNode.findNode(countryNode, city);
                    if(cityNode2==null){  //checking if duplicate city is getting inserted
                        countryNode.child.add(new TreeNode(city, null));
                    }

                } else {                    //if in same continent new country is getting inserted
                    List<TreeNode> cities = new ArrayList<TreeNode>();
                    TreeNode cityNode = new TreeNode(city, null);
                    cities.add(cityNode);

                    TreeNode tempCountry = new TreeNode(country, cities);

                    continentNode.child.add(tempCountry);
                }
            } else {
                List<TreeNode> cities = new ArrayList<TreeNode>();
                TreeNode cityNode = new TreeNode(city, null);
                cities.add(cityNode);

                List<TreeNode> countries = new ArrayList<>();
                countries.add(new TreeNode(country, cities));

                TreeNode tempContinentNode = new TreeNode(continent, countries);

                parentNode.child.add(tempContinentNode);
            }
        }
    }

/*    private static TreeNode findNode(TreeNode node, String name) {
        TreeNode resultNode = null;
        for (TreeNode child : node.child) {
            if (child.name.equals(name)) {
                resultNode = child;
                break;
            }
        }
        return resultNode;
    }*/
}
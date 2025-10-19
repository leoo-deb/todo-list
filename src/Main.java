import todolist.SharesList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SharesList shares = new SharesList();
        Scanner sc = new Scanner(System.in);
        String enter;
        int op2;

        do {
            SharesList.viewMenu();
            op2 = sc.nextInt();
            sc.nextLine();

            if (op2 == 1) {
                shares.addList();
            }

            if (op2 == 2) {
                shares.viewManager();
            }

            if (op2 == 3) {
                shares.viewList();
            }
        } while (op2 != 4);
    }
}
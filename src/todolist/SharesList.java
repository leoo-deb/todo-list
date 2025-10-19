package todolist;

import java.util.ArrayList;
import java.util.Scanner;

public class SharesList {
    private String op;
    private ArrayList<Todo> list2;
    private Scanner sc = new Scanner(System.in);

    public SharesList() {
        this.list2 = new ArrayList<>();
    }

    public static void viewMenu() {
        System.out.println("""
                ---------HOME PAGE-----------
                [1] Criar tarefas
                [2] Gerenciar tarefas
                [3] Ver tarefas
                [4] Sair
                -----------------------------""");
        System.out.print("Escolha uma opcao: ");
    }

    public void addList() {
        do {
            System.out.println("-----------------------------");
            System.out.print("Digite o nome da tarefa: ");
            String name = sc.nextLine();

            System.out.print("Digite a descricao da tarefa: ");
            String desc = sc.nextLine();
            System.out.println("-----------------------------");

            if (!name.matches("^[a-zA-Z\\s]{3,16}$")) {
                System.out.println("ERROR: O nome deve ter no minimo 3 caracteres.");
                break;
            }
            if (!desc.matches("^[a-zA-Z\\s]{5,100}$")) {
                System.out.println("ERROR: A descricao deve ter no minimo 3 caracteres.");
                break;
            }

            list2.add(new Todo(name, desc));
            System.out.println("Tarefa criada com sucesso!");

            System.out.print("Para voltar para o inicio pressione (ENTER).");
            op = sc.nextLine();
        } while (!op.isBlank());
    }

    public void viewManager() {
        if (!(list2.isEmpty())) {
            int op2;
            do {
                System.out.println("""
                        --------MANAGER TASK---------
                        [1] Terminar tarefa
                        [2] Editar tarefa
                        [3] Sair
                        -----------------------------""");
                System.out.print("Escolha uma opcao: ");
                op2 = sc.nextInt();
                sc.nextLine();

                if (op2 == 1) {
                    do {
                        viewIndex();
                        System.out.print("""
                                Qual tarefa sera marcada como concluida 
                                (OBS: Digite apenas o numero/index listado acima): """);
                        int index = sc.nextInt();
                        sc.nextLine();
                        try {
                            list2.remove(index);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("-----------------------------");
                            System.out.format("""
                                    ERROR: Nao possui nenhum index (%d) no sistema. Digite apenas 
                                    index que esteja criado no sistema.
                                    """, index);
                            break;
                        }

                        System.out.println("Tarefa concluida com sucesso!");
                        System.out.println("-----------------------------");
                        System.out.print("Para voltar para o inicio pressione (ENTER).");
                        op = sc.nextLine();
                    } while (!op.isBlank());
                    break;
                }

                if (op2 == 2) {
                    do {
                        viewIndex();
                        System.out.print("""
                                Qual tarefa sera editada 
                                (OBS: Digite apenas o numero/index listado acima): """);
                        int index = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Digite o novo titulo: ");
                        String newTitle = sc.nextLine();

                        System.out.print("Digite a nova descricao: ");
                        String newDesc = sc.nextLine();

                        try {
                            list2.set(index, new Todo(newTitle, newDesc));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("-----------------------------");
                            System.out.format("""
                                    ERROR: Nao possui nenhum index (%d) no sistema. Digite apenas 
                                    index que esteja criado no sistema.
                                    """, index);
                            break;
                        }

                        System.out.println("Tarefa concluida com sucesso!");
                        System.out.println("-----------------------------");
                        System.out.print("Para voltar para o inicio pressione (ENTER).");
                        op = sc.nextLine();
                    } while (!op.isBlank());
                    break;
                }
            } while (op2 != 3);
        } else {
            System.out.println("Ainda nao existe uma lista para ser gerenciada.");
        }
    }

    private void viewIndex() {
        if (!(list2.isEmpty())) {
            for (Todo a : list2) {
                System.out.format("-----------INDEX %d-----------\n", list2.indexOf(a));
                System.out.println(a);
            }
            System.out.println("-----------------------------");
        } else {
            System.out.println("Ainda nao existe nenhuma lista criada no momento.");
        }
    }

    public void viewList() {
        if (!(list2.isEmpty())) {
            do {
                for (Todo a : list2) {
                    System.out.format("-----------TAREFA %d----------\n", list2.indexOf(a) + 1);
                    System.out.println(a);
                }

                System.out.println("-----------------------------");
                System.out.print("Para voltar para o inicio pressione (ENTER).");
                op = sc.nextLine();
            } while (!op.isBlank());
        } else {
            System.out.println("Ainda nao existe nenhuma lista criada no momento.");
        }
    }
}

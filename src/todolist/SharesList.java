package todolist;

import java.util.*;

public class SharesList {
    private String op;
    private final List<Todo> list2;
    private final Scanner sc = new Scanner(System.in);

    public SharesList() {
        this.list2 = new ArrayList<>();
    }

    public static void viewMenu() {
        System.out.println("""
                ---------HOME PAGE-----------
                [1] Criar tarefas
                [2] Buscar tarefas
                [3] Gerenciar tarefas
                [4] Ver tarefas
                [5] Sair
                -----------------------------""");
        System.out.print("Escolha uma opcao: ");
    }

    public void addList() {
        String name, desc;

        do {
            System.out.println("-----------------------------");
            while (true) {
                System.out.print("Digite o nome da tarefa: ");
                name = sc.nextLine();

                if (name.matches("^[a-zA-Z\\s\\d]{3,16}$")) {
                    for (Todo nameEqual : list2) {
                        if (nameEqual.getName().equals(name)) {
                            System.out.println("ERROR: Ja possui uma lista com este nome.");
                        }
                        return;
                    }
                    break;
                }
                System.out.println("ERROR: O nome deve ter no minimo 3 caracteres.");
            }

            while (true) {
                System.out.print("Digite a descricao da tarefa: ");
                desc = sc.nextLine();
                if (desc.matches("^[a-zA-Z\\s\\d]{5,100}$")) {
                    break;
                }
                System.out.println("ERROR: A descricao deve ter no minimo 5 caracteres.");
            }
            System.out.println("-----------------------------");

            list2.add(new Todo(name, desc));
            System.out.println("Tarefa criada com sucesso!");

            System.out.print("Para voltar para o inicio pressione (ENTER).");
            op = sc.nextLine();
        } while (!op.isBlank());
    }

    public void findList() {
        String name, opt;
        int find;

        if (!list2.isEmpty()) {
            System.out.println("""
                        ----------FIND TASK----------
                        [1] Realizar nova busca
                        [2] Sair
                        -----------------------------""");
            System.out.print("Escolha uma opcao: ");
            find = sc.nextInt();
            sc.nextLine();

            do {
                if (find == 1) {
                    List<Todo> findList = new ArrayList<>();
                    System.out.print("Digite o nome: ");
                    name = sc.nextLine().toLowerCase();

                    for (Todo result : list2) {
                        if (result.getName().contains(name)) {
                            findList.add(result);
                        }
                    }

                    if (findList.isEmpty()) {
                        System.out.println("Nenhuma tarefa encontrada.");
                    } else {
                        System.out.println("-----------------------------");
                        System.out.println("Terefa(s) encontrada(s): " + findList.size());
                        for (Todo resultList : findList) {
                            System.out.format("-----------TAREFA %d----------\n", list2.indexOf(resultList) + 1);
                            System.out.println(resultList);
                        }
                        System.out.println("-----------------------------");
                    }
                }
                System.out.print("Deseja fazer uma nova busca? [S/N] ");
                opt = sc.next().toUpperCase();
                sc.nextLine();
            } while (!opt.equals("N"));
        } else {
            System.out.println("ERROR: Ainda nao existe uma lista para ser buscada.");
        }
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
            System.out.println("ERROR: Ainda nao existe uma lista para ser gerenciada.");
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
            System.out.println("ERROR: Ainda nao existe nenhuma lista criada no momento.");
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
            System.out.println("ERROR: Ainda nao existe nenhuma lista criada no momento.");
        }
    }
}

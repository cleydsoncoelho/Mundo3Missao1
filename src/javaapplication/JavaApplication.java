package javaapplication;

import model.*;
import java.util.Scanner;

public class JavaApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("===================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("===================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o id da pessoa: ");
                    int id = scanner.nextInt(); scanner.nextLine();
                    System.out.println("Insira os dados...");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    if (tipo.equals("F")) {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt(); scanner.nextLine();
                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipo.equals("J")) {
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();
                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                }
                case 2 -> {
                    System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o id da pessoa: ");
                    int id = scanner.nextInt(); scanner.nextLine();
                    if (tipo.equals("F")) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            pf.exibir();
                            System.out.println("Insira os novos dados...");
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.print("Idade: ");
                            int idade = scanner.nextInt(); scanner.nextLine();
                            repoFisica.alterar(new PessoaFisica(id, nome, cpf, idade));
                        } else {
                            System.out.println("Pessoa Física não encontrada.");
                        }
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            pj.exibir();
                            System.out.println("Insira os novos dados...");
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("CNPJ: ");
                            String cnpj = scanner.nextLine();
                            repoJuridica.alterar(new PessoaJuridica(id, nome, cnpj));
                        } else {
                            System.out.println("Pessoa Jurídica não encontrada.");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o id da pessoa: ");
                    int id = scanner.nextInt(); scanner.nextLine();
                    if (tipo.equals("F")) {
                        repoFisica.excluir(id);
                    } else if (tipo.equals("J")) {
                        repoJuridica.excluir(id);
                    }
                }
                case 4 -> {
                    System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o id da pessoa: ");
                    int id = scanner.nextInt(); scanner.nextLine();
                    if (tipo.equals("F")) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) pf.exibir();
                        else System.out.println("Pessoa Física não encontrada.");
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) pj.exibir();
                        else System.out.println("Pessoa Jurídica não encontrada.");
                    }
                }
                case 5 -> {
                    System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
                    String tipo = scanner.nextLine().toUpperCase();
                    if (tipo.equals("F")) {
                        for (PessoaFisica pf : repoFisica.obterTodos()) pf.exibir();
                    } else if (tipo.equals("J")) {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) pj.exibir();
                    }
                }
                case 6 -> {
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixo = scanner.nextLine();
                    try {
                        repoFisica.persistir(prefixo + ".fisica.bin");
                        repoJuridica.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar dados: " + e.getMessage());
                    }
                }
                case 7 -> {
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixo = scanner.nextLine();
                    try {
                        repoFisica.recuperar(prefixo + ".fisica.bin");
                        repoJuridica.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar dados: " + e.getMessage());
                    }
                }
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}

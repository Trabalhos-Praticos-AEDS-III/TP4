package Menu;

import Arquivo.Backup;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuBackup 
{
    private static Backup backup;
    private static Scanner sc = new Scanner(System.in); // Scanner para entrada de dados


    public MenuBackup () throws Exception {
        backup = new Backup( );
    } // BackupView ( )


    public void menu() throws Exception
    {
        int opcao;
        System.out.println("\nAEDs-III 1.0           ");
        System.out.println("-------------------------");
        System.out.println("> Início > Backup        ");
        System.out.println("1 - Realizar Backup      ");
        System.out.println("2 - Restaurar Backup     ");
        System.out.println("0 - Voltar               ");
        System.out.print  ("Opção: "                  );
        try
        {
            opcao = Integer.valueOf(sc.nextLine()); // Lê a escolha do usuário
        }
        catch (NumberFormatException e)
        {
            opcao = -1; // Se ocorrer erro, define opção como inválida
        }
    
        switch( opcao ) 
        {
            case 0:
                break;
            case 1:
                realizarBackup( );
                break;
            case 2:
                restaurarBackup( );
                break;
            
            default:
                System.err.println("Opção inválida.");
        }
        
    } 
    public static String getDataHoraAtual() 
    {
        return ( LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd--HH-mm-ss")) );
    } 

    public static void realizarBackup( ) throws Exception
    {
        System.out.println( "\n> Relizando Backup:" );
        try
        {
            System.out.println( "\nConfirma a realização do backup? (S/N)" );
            char resp = sc.nextLine( ).charAt(0);

            if( resp == 'S' || resp == 's' ) 
            {
                backup.createBackup(getDataHoraAtual( )+".db" );
                System.out.println("Backup realizado com sucesso." );
            } else {
                System.out.println("Operação cancelada." );
            } // if
        } catch ( Exception e ) {
            System.err.println( "Erro ao realizar backup." );
        } // try-catch
    } // realizarBackup ( )

    public static void restaurarBackup( ) throws Exception
    {
        System.out.println( "\n> Restaurar Backup:" );
        
        try
        {
            ArrayList<String> backupsList =  backup.listBackups( );

            if( !backupsList.isEmpty( ) ) 
            {
                System.out.print( "ID do arquivo de backup: " );
                String input = sc.nextLine( );
    
                if( input.length( ) > 0 ) 
                {
                    int idBackup = Integer.parseInt( input );
                    backup.restoreBackup( backupsList.get( idBackup-1 )+".db" );
                    System.out.println(  "Backup restaurado com sucesso."  );
                } else {
                    System.err.println(  "ID inválido. Operação cancelada!" );
                } // if
                
            } // if
        } catch ( Exception e ) {
            System.err.println( "Erro ao restaurar backup." );
        } // try-catch
    } // restaurarBackup ( )

} // BackUpView

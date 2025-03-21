package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reserva;

public class Programa {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

		// INSERINDO DADOS DA RESERVA: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		System.out.print("Nº do quarto: ");
		int numeroDoQuarto = sc.nextInt();
		System.out.print("Data de check-in (dd/MM/aaaa): ");
		Date dataDeCheckIn = dataFormatada.parse(sc.next());
		System.out.print("Data de check-out (dd/MM/aaaa): ");
		Date dataDeCheckOut = dataFormatada.parse(sc.next());
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

		// REGRASS DE NEGÓCIO, (VERIFICAÇÃO DE ERRO) >->->->->->->->->

		// CONVERTER Date PARA LocalDate
		LocalDate checkIn = dataDeCheckIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // <----- Conversão
																									// de Date para
																									// LocalDate
		LocalDate checkOut = dataDeCheckOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // <-----
																										// Conversão de
																										// Date para
																										// LocalDate

		// VERIFICAR SE A DATA DE CHECK-OUT NÃO É POSTERIOR AO CHECK-IN
		if (!checkOut.isAfter(checkIn)) {// before?
			System.out.println("Erro na reserva: Data do check-out deve ser posterior a data de check-in");
		} else {
			Reserva novaReserva = new Reserva(numeroDoQuarto, checkIn, checkOut); // <----- Construtor corrigido para
																					// LocalDate
			System.out.println("Reserva realizada com sucesso:");
			System.out.println(novaReserva);

			// ATUALIZADO DATA ------------------------------------
			System.out.println();
			System.out.println("Entre com as datas para ATUALIZAÇÃO:");
			System.out.print("Data de check-in (dd/MM/aaaa): ");
			Date novaDataDeCheckIn = dataFormatada.parse(sc.next()); // <----- Nova variável para check-in
			System.out.print("Data de check-out (dd/MM/aaaa): ");
			Date novaDataDeCheckOut = dataFormatada.parse(sc.next()); // <----- Nova variável para check-out

			// Converter as novas datas para LocalDate
			LocalDate novoCheckIn = novaDataDeCheckIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // <-----
																												// Conversão
			LocalDate novoCheckOut = novaDataDeCheckOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // <-----
																													// Conversão

			// Verificar se a nova data de check-out é posterior à nova data de check-in
			if (!novoCheckOut.isAfter(novoCheckIn)) {
				System.out.println("Erro na atualização: Data do check-out deve ser posterior à data de check-in");
			} else {

				// AS DATAS DEVEM SER FUTURAS --
				LocalDate dataAtual = LocalDate.now();
				if (novoCheckIn.isBefore(dataAtual) || novoCheckOut.isBefore(dataAtual)) {
					System.out.println("Erro: As datas devem ser futuras.");
				} else {
			        novaReserva.atualizarDatas(novoCheckIn, novoCheckOut);
			        System.out.println("Reserva atualizada com sucesso:");
			        System.out.println(novaReserva);
				}// -----------------------------

			}
			// -----------------------------------------------------
		}

		// <-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<

		sc.close();
	}//

}

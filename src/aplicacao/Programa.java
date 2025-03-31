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

		// CONVERTER Date PARA LocalDate
		// 1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>1>
		// A ENTRADA USA VERSÕES 'DATE' ANTIGAS. POR ISSO A CONVERSÃO DESSE BLOCO
		LocalDate checkIn = dataDeCheckIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convers checkIn
		LocalDate checkOut = dataDeCheckOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // ConvercheckOut
		// 1<1<1<1<1<1<1<11<1<1<1<1<1<1<11<1<1<1<1<1<1<11<1<1<1<1<1<1<11<1<1<1<1<1<1<11<1<1<1<1<1<1<11<1<1<1<1<1<

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

			String erroString = novaReserva.atualizarDatas(novoCheckIn, novoCheckOut);
			if (erroString != null) {
				System.out.println("Erro na reserva: " + erroString);

			} else {
				System.out.println("Reserva atualizada com sucesso:");
				System.out.println(novaReserva);
			}
			// -----------------------------------------------------
		}

		// <-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<

		sc.close();
	}//

}

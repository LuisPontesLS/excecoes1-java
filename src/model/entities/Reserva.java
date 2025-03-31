package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter; //Adicionado para formatar LocalDate
import java.time.temporal.ChronoUnit;

public class Reserva {

// ATRIBUTOS
	private Integer numeroDoQuarto;
	private LocalDate checkIn;
	private LocalDate checkOut;

	private static DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");

// CONSTRUTOR
	public Reserva(Integer numeroDoQuarto, LocalDate checkin, LocalDate checkout) {
		this.numeroDoQuarto = numeroDoQuarto;
		this.checkIn = checkin;
		this.checkOut = checkout;
	}

// MÉTODOS ACESSORES E MODIFICADORES

	// Nº QUARTO
	public Integer getNumeroDoQuarto() {
		return numeroDoQuarto;
	}

	public void setNumeroDoQuarto(Integer numeroDoQuarto) {
		this.numeroDoQuarto = numeroDoQuarto;
	}

	// PEGAR DATA CHECKIN
	public LocalDate getCheckin() {
		return checkIn;
	}

	// setCheckIn EXCLUÍDO, POIS A ALTERAÇÃO É FEITA PELO MÉTODO ATUALIZARDATA

	// PEGAR DATA CHECKOUT
	public LocalDate getCheckout() {
		return checkOut;
	}

	// setChekOut EXCLUÍDO, POIS A ALTERAÇÃO É FEITA PELO MÉTODO ATUALIZARDATA

// CALCULAR A DURAÇÃO EM DIAS BASEADO NO CHECKOUT E CHECKIN
	public long duracao() {
		return ChronoUnit.DAYS.between(checkIn, checkOut);
	}

// ATUALIZANDO DATAS DE ENTRADA E SAÍDA
	public String atualizarDatas(LocalDate novoCheckIn, LocalDate novoCheckOut) {
	
		// AS DATAS DEVEM SER FUTURAS (EM RELAÇÃO A DATA ATUAL) --
		LocalDate dataAtual = LocalDate.now();
		if (novoCheckIn.isBefore(dataAtual) || novoCheckOut.isBefore(dataAtual)) {
			return "Erro: As datas devem ser futuras.";
		}
		
		// Verificar se a nova data de check-out é posterior à nova data de check-in
		if (!novoCheckOut.isAfter(novoCheckIn)) {
			return "Erro na atualização: Data do check-out deve ser posterior à data de check-in";
		}

		//SE PASSAR PELOS DOIS 'IF's' AÍ SIM ATUALIZA AS DATAS
		this.checkIn = novoCheckIn;
		this.checkOut = novoCheckOut;
		
		return null; //se não voltar nenhuma String (de erro) então deu certo
	}

	@Override
	public String toString() {
		return "Reserva Nº do quarto: " + numeroDoQuarto + ", checkIn: " + checkIn.format(dataFormatada)
				+ ", checkOut: " + checkOut.format(dataFormatada) // tirar a formatação
				+ ", " + duracao() + " noites";
	}

}

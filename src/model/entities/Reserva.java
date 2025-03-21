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
	public Integer getNumeroDoQuarto() {
		return numeroDoQuarto;
	}

	public void setNumeroDoQuarto(Integer numeroDoQuarto) {
		this.numeroDoQuarto = numeroDoQuarto;
	}

	public LocalDate getCheckin() {
		return checkIn;
	}
	
	//setCheckIn EXCLUÍDO, POIS A ALTERAÇÃO É FEITA PELO MÉTODO ATUALIZARDATA
	
	public LocalDate getCheckout() {
		return checkOut;
	}
	
	//setChekOut EXCLUÍDO, POIS A ALTERAÇÃO É FEITA PELO MÉTODO ATUALIZARDATA

// CALCULAR A DURAÇÃO EM DIAS BASEADO NO CHECKOUT E CHECKIN
	 public long duracao() {
	        return ChronoUnit.DAYS.between(checkIn, checkOut);
	    }
	 
// ATUALIZANDO DATAS DE ENTRADA E SAÍDA
	 public void atualizarDatas(LocalDate novoCheckIn, LocalDate novoCheckOut) {
		 this.checkIn = novoCheckIn;
		 this.checkOut = novoCheckOut;		 
	 }

	@Override
	public String toString() {
		return "Reserva Nº do quarto: " + numeroDoQuarto 
				+ ", checkIn: " 
				+ checkIn.format(dataFormatada)
				+ ", checkOut: " 
				+ checkOut.format(dataFormatada) //tirar a formatação
				+ ", "
				+ duracao() 
				+ " noites";
	}
	
	

}

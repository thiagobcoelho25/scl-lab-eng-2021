package model;

public class IngredienteAcrescimo {
		private String nome;
		private Double valor;
		private Integer quantidade;
		private Ingrediente ingrediente;
		
		public IngredienteAcrescimo() {}
		
		public IngredienteAcrescimo(Ingrediente ingrediente, Integer quantidade) {
			this.nome = ingrediente.getNome();
			this.valor = ingrediente.getValor();
			this.quantidade = quantidade;
			this.ingrediente = ingrediente;
		}
		
		public String getNome() {
			return this.nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public Double getValor() {
			return this.valor;
		}
		public void setValor(Double valor) {
			this.valor = valor;
		}
		public Integer getQuantidade() {
			return this.quantidade;
		}
		public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
		}
		
		public Ingrediente getIngrediente() {
			return this.ingrediente;
		}

}

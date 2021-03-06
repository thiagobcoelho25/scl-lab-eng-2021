PGDMP     	    7                y            scl    11.5    11.5 Q    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            ?           1262    19424    scl    DATABASE     ?   CREATE DATABASE scl WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE scl;
             postgres    false            ?            1259    19425 
   acrescimos    TABLE     ?   CREATE TABLE public.acrescimos (
    quantidade integer,
    ingrediente_id integer NOT NULL,
    itenspedido_id integer NOT NULL
);
    DROP TABLE public.acrescimos;
       public         postgres    false            ?            1259    19432    bairro    TABLE     ?   CREATE TABLE public.bairro (
    id integer NOT NULL,
    entregavel character varying(255),
    frete double precision,
    nome character varying(255)
);
    DROP TABLE public.bairro;
       public         postgres    false            ?            1259    19430    bairro_id_seq    SEQUENCE     ?   ALTER TABLE public.bairro ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.bairro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    198            ?            1259    19442    cliente    TABLE     ?   CREATE TABLE public.cliente (
    id integer NOT NULL,
    nome character varying(255),
    numero integer,
    ponto_referencia character varying(255),
    rua character varying(255),
    bairro_id integer
);
    DROP TABLE public.cliente;
       public         postgres    false            ?            1259    19440    cliente_id_seq    SEQUENCE     ?   ALTER TABLE public.cliente ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    200            ?            1259    19452    entrega    TABLE     ?   CREATE TABLE public.entrega (
    id integer NOT NULL,
    hora_saida timestamp without time zone,
    status character varying(255)
);
    DROP TABLE public.entrega;
       public         postgres    false            ?            1259    19450    entrega_id_seq    SEQUENCE     ?   ALTER TABLE public.entrega ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.entrega_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    202            ?            1259    19459    estoque    TABLE     Q   CREATE TABLE public.estoque (
    id integer NOT NULL,
    quantidade integer
);
    DROP TABLE public.estoque;
       public         postgres    false            ?            1259    19457    estoque_id_seq    SEQUENCE     ?   ALTER TABLE public.estoque ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.estoque_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    204            ?            1259    19464    funcionario    TABLE     ?   CREATE TABLE public.funcionario (
    cargo character varying(20),
    foto character varying(255),
    login character varying(20),
    salario double precision,
    senha character varying(20),
    id integer NOT NULL
);
    DROP TABLE public.funcionario;
       public         postgres    false            ?            1259    19469    gerente    TABLE     {   CREATE TABLE public.gerente (
    login character varying(20),
    senha character varying(20),
    id integer NOT NULL
);
    DROP TABLE public.gerente;
       public         postgres    false            ?            1259    19476    ingrediente    TABLE     ?   CREATE TABLE public.ingrediente (
    id integer NOT NULL,
    nome character varying(255),
    valor double precision,
    estoque_id integer
);
    DROP TABLE public.ingrediente;
       public         postgres    false            ?            1259    19474    ingrediente_id_seq    SEQUENCE     ?   ALTER TABLE public.ingrediente ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.ingrediente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    208            ?            1259    19483    itens_pedido    TABLE     m   CREATE TABLE public.itens_pedido (
    id integer NOT NULL,
    pedido_id integer,
    produto_id integer
);
     DROP TABLE public.itens_pedido;
       public         postgres    false            ?            1259    19481    itens_pedido_id_seq    SEQUENCE     ?   ALTER TABLE public.itens_pedido ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.itens_pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    210            ?            1259    19490 	   pagamento    TABLE     ?   CREATE TABLE public.pagamento (
    id integer NOT NULL,
    desconto double precision,
    forma_de_pagamento character varying(255),
    valor double precision
);
    DROP TABLE public.pagamento;
       public         postgres    false            ?            1259    19488    pagamento_id_seq    SEQUENCE     ?   ALTER TABLE public.pagamento ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.pagamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    212            ?            1259    19497    pedido    TABLE     ?   CREATE TABLE public.pedido (
    id integer NOT NULL,
    data timestamp without time zone,
    cliente_id integer NOT NULL,
    entrega_id integer,
    pagamento_id integer,
    usuario_id integer NOT NULL
);
    DROP TABLE public.pedido;
       public         postgres    false            ?            1259    19495    pedido_id_seq    SEQUENCE     ?   ALTER TABLE public.pedido ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    214            ?            1259    19504    produto    TABLE     |   CREATE TABLE public.produto (
    id integer NOT NULL,
    nome character varying(255),
    preco_final double precision
);
    DROP TABLE public.produto;
       public         postgres    false            ?            1259    19502    produto_id_seq    SEQUENCE     ?   ALTER TABLE public.produto ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    216            ?            1259    19509    produtos_ingredientes    TABLE     ?   CREATE TABLE public.produtos_ingredientes (
    quantidade integer,
    tipo integer,
    ingrediente_id integer NOT NULL,
    produto_id integer NOT NULL
);
 )   DROP TABLE public.produtos_ingredientes;
       public         postgres    false            ?            1259    19516    usuario    TABLE     ?   CREATE TABLE public.usuario (
    id integer NOT NULL,
    nome character varying(255),
    numero integer,
    rua character varying(255),
    bairro_id integer
);
    DROP TABLE public.usuario;
       public         postgres    false            ?            1259    19514    usuario_id_seq    SEQUENCE     ?   ALTER TABLE public.usuario ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    219            j          0    19425 
   acrescimos 
   TABLE DATA               P   COPY public.acrescimos (quantidade, ingrediente_id, itenspedido_id) FROM stdin;
    public       postgres    false    196   ?]       l          0    19432    bairro 
   TABLE DATA               =   COPY public.bairro (id, entregavel, frete, nome) FROM stdin;
    public       postgres    false    198   ?]       n          0    19442    cliente 
   TABLE DATA               U   COPY public.cliente (id, nome, numero, ponto_referencia, rua, bairro_id) FROM stdin;
    public       postgres    false    200   ?]       p          0    19452    entrega 
   TABLE DATA               9   COPY public.entrega (id, hora_saida, status) FROM stdin;
    public       postgres    false    202   ^       r          0    19459    estoque 
   TABLE DATA               1   COPY public.estoque (id, quantidade) FROM stdin;
    public       postgres    false    204   (^       s          0    19464    funcionario 
   TABLE DATA               M   COPY public.funcionario (cargo, foto, login, salario, senha, id) FROM stdin;
    public       postgres    false    205   E^       t          0    19469    gerente 
   TABLE DATA               3   COPY public.gerente (login, senha, id) FROM stdin;
    public       postgres    false    206   b^       v          0    19476    ingrediente 
   TABLE DATA               B   COPY public.ingrediente (id, nome, valor, estoque_id) FROM stdin;
    public       postgres    false    208   ^       x          0    19483    itens_pedido 
   TABLE DATA               A   COPY public.itens_pedido (id, pedido_id, produto_id) FROM stdin;
    public       postgres    false    210   ?^       z          0    19490 	   pagamento 
   TABLE DATA               L   COPY public.pagamento (id, desconto, forma_de_pagamento, valor) FROM stdin;
    public       postgres    false    212   ?^       |          0    19497    pedido 
   TABLE DATA               \   COPY public.pedido (id, data, cliente_id, entrega_id, pagamento_id, usuario_id) FROM stdin;
    public       postgres    false    214   ?^       ~          0    19504    produto 
   TABLE DATA               8   COPY public.produto (id, nome, preco_final) FROM stdin;
    public       postgres    false    216   ?^                 0    19509    produtos_ingredientes 
   TABLE DATA               ]   COPY public.produtos_ingredientes (quantidade, tipo, ingrediente_id, produto_id) FROM stdin;
    public       postgres    false    217   _       ?          0    19516    usuario 
   TABLE DATA               C   COPY public.usuario (id, nome, numero, rua, bairro_id) FROM stdin;
    public       postgres    false    219   -_       ?           0    0    bairro_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.bairro_id_seq', 1, false);
            public       postgres    false    197            ?           0    0    cliente_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.cliente_id_seq', 1, false);
            public       postgres    false    199            ?           0    0    entrega_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.entrega_id_seq', 1, false);
            public       postgres    false    201            ?           0    0    estoque_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.estoque_id_seq', 1, false);
            public       postgres    false    203            ?           0    0    ingrediente_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.ingrediente_id_seq', 1, false);
            public       postgres    false    207            ?           0    0    itens_pedido_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.itens_pedido_id_seq', 1, false);
            public       postgres    false    209            ?           0    0    pagamento_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.pagamento_id_seq', 1, false);
            public       postgres    false    211            ?           0    0    pedido_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.pedido_id_seq', 1, false);
            public       postgres    false    213            ?           0    0    produto_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.produto_id_seq', 1, false);
            public       postgres    false    215            ?           0    0    usuario_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.usuario_id_seq', 1, false);
            public       postgres    false    218            ?
           2606    19429    acrescimos acrescimos_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.acrescimos
    ADD CONSTRAINT acrescimos_pkey PRIMARY KEY (ingrediente_id, itenspedido_id);
 D   ALTER TABLE ONLY public.acrescimos DROP CONSTRAINT acrescimos_pkey;
       public         postgres    false    196    196            ?
           2606    19439    bairro bairro_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.bairro
    ADD CONSTRAINT bairro_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.bairro DROP CONSTRAINT bairro_pkey;
       public         postgres    false    198            ?
           2606    19449    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public         postgres    false    200            ?
           2606    19456    entrega entrega_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.entrega
    ADD CONSTRAINT entrega_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.entrega DROP CONSTRAINT entrega_pkey;
       public         postgres    false    202            ?
           2606    19463    estoque estoque_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.estoque
    ADD CONSTRAINT estoque_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.estoque DROP CONSTRAINT estoque_pkey;
       public         postgres    false    204            ?
           2606    19468    funcionario funcionario_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_pkey;
       public         postgres    false    205            ?
           2606    19473    gerente gerente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT gerente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.gerente DROP CONSTRAINT gerente_pkey;
       public         postgres    false    206            ?
           2606    19480    ingrediente ingrediente_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.ingrediente
    ADD CONSTRAINT ingrediente_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.ingrediente DROP CONSTRAINT ingrediente_pkey;
       public         postgres    false    208            ?
           2606    19487    itens_pedido itens_pedido_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.itens_pedido
    ADD CONSTRAINT itens_pedido_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.itens_pedido DROP CONSTRAINT itens_pedido_pkey;
       public         postgres    false    210            ?
           2606    19494    pagamento pagamento_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT pagamento_pkey;
       public         postgres    false    212            ?
           2606    19501    pedido pedido_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.pedido DROP CONSTRAINT pedido_pkey;
       public         postgres    false    214            ?
           2606    19508    produto produto_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.produto DROP CONSTRAINT produto_pkey;
       public         postgres    false    216            ?
           2606    19513 0   produtos_ingredientes produtos_ingredientes_pkey 
   CONSTRAINT     ?   ALTER TABLE ONLY public.produtos_ingredientes
    ADD CONSTRAINT produtos_ingredientes_pkey PRIMARY KEY (ingrediente_id, produto_id);
 Z   ALTER TABLE ONLY public.produtos_ingredientes DROP CONSTRAINT produtos_ingredientes_pkey;
       public         postgres    false    217    217            ?
           2606    19523    usuario usuario_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         postgres    false    219            ?
           2606    19524 &   acrescimos fk2i3utjvvqspl8b763ujskqagn    FK CONSTRAINT     ?   ALTER TABLE ONLY public.acrescimos
    ADD CONSTRAINT fk2i3utjvvqspl8b763ujskqagn FOREIGN KEY (ingrediente_id) REFERENCES public.ingrediente(id);
 P   ALTER TABLE ONLY public.acrescimos DROP CONSTRAINT fk2i3utjvvqspl8b763ujskqagn;
       public       postgres    false    2773    208    196            ?
           2606    19564 "   pedido fk30s8j2ktpay6of18lbyqn3632    FK CONSTRAINT     ?   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk30s8j2ktpay6of18lbyqn3632 FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);
 L   ALTER TABLE ONLY public.pedido DROP CONSTRAINT fk30s8j2ktpay6of18lbyqn3632;
       public       postgres    false    200    214    2763            ?
           2606    19559 (   itens_pedido fk3uo2duj20r2i4mwplv4vb26u6    FK CONSTRAINT     ?   ALTER TABLE ONLY public.itens_pedido
    ADD CONSTRAINT fk3uo2duj20r2i4mwplv4vb26u6 FOREIGN KEY (produto_id) REFERENCES public.produto(id);
 R   ALTER TABLE ONLY public.itens_pedido DROP CONSTRAINT fk3uo2duj20r2i4mwplv4vb26u6;
       public       postgres    false    2781    216    210            ?
           2606    19579 "   pedido fk6uxomgomm93vg965o8brugt00    FK CONSTRAINT     ?   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk6uxomgomm93vg965o8brugt00 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);
 L   ALTER TABLE ONLY public.pedido DROP CONSTRAINT fk6uxomgomm93vg965o8brugt00;
       public       postgres    false    214    219    2785            ?
           2606    19594 #   usuario fk7i07vbvhoesll0w24pc36ohoq    FK CONSTRAINT     ?   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk7i07vbvhoesll0w24pc36ohoq FOREIGN KEY (bairro_id) REFERENCES public.bairro(id);
 M   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk7i07vbvhoesll0w24pc36ohoq;
       public       postgres    false    219    198    2761            ?
           2606    19534 #   cliente fk8y9qoaf9mg3nj79rm9unjdcjc    FK CONSTRAINT     ?   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk8y9qoaf9mg3nj79rm9unjdcjc FOREIGN KEY (bairro_id) REFERENCES public.bairro(id);
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk8y9qoaf9mg3nj79rm9unjdcjc;
       public       postgres    false    198    200    2761            ?
           2606    19589 1   produtos_ingredientes fka6lwxubpa84ukncsajmsqrhay    FK CONSTRAINT     ?   ALTER TABLE ONLY public.produtos_ingredientes
    ADD CONSTRAINT fka6lwxubpa84ukncsajmsqrhay FOREIGN KEY (produto_id) REFERENCES public.produto(id);
 [   ALTER TABLE ONLY public.produtos_ingredientes DROP CONSTRAINT fka6lwxubpa84ukncsajmsqrhay;
       public       postgres    false    2781    217    216            ?
           2606    19574 "   pedido fkd8wwc5gy5hnijj7iki22jeorn    FK CONSTRAINT     ?   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fkd8wwc5gy5hnijj7iki22jeorn FOREIGN KEY (pagamento_id) REFERENCES public.pagamento(id);
 L   ALTER TABLE ONLY public.pedido DROP CONSTRAINT fkd8wwc5gy5hnijj7iki22jeorn;
       public       postgres    false    212    2777    214            ?
           2606    19554 (   itens_pedido fkdrlbqwi9u15b2nnaj826e229x    FK CONSTRAINT     ?   ALTER TABLE ONLY public.itens_pedido
    ADD CONSTRAINT fkdrlbqwi9u15b2nnaj826e229x FOREIGN KEY (pedido_id) REFERENCES public.pedido(id);
 R   ALTER TABLE ONLY public.itens_pedido DROP CONSTRAINT fkdrlbqwi9u15b2nnaj826e229x;
       public       postgres    false    2779    214    210            ?
           2606    19549 '   ingrediente fkekueot0yd7b27gievhqien7f6    FK CONSTRAINT     ?   ALTER TABLE ONLY public.ingrediente
    ADD CONSTRAINT fkekueot0yd7b27gievhqien7f6 FOREIGN KEY (estoque_id) REFERENCES public.estoque(id);
 Q   ALTER TABLE ONLY public.ingrediente DROP CONSTRAINT fkekueot0yd7b27gievhqien7f6;
       public       postgres    false    2767    204    208            ?
           2606    19569 "   pedido fkevm8nwtd6mwuj3jy0oc4brbil    FK CONSTRAINT     ?   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fkevm8nwtd6mwuj3jy0oc4brbil FOREIGN KEY (entrega_id) REFERENCES public.entrega(id);
 L   ALTER TABLE ONLY public.pedido DROP CONSTRAINT fkevm8nwtd6mwuj3jy0oc4brbil;
       public       postgres    false    214    202    2765            ?
           2606    19544 #   gerente fkfrnjv0h1r1ce51i1gjgj8p4a9    FK CONSTRAINT        ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT fkfrnjv0h1r1ce51i1gjgj8p4a9 FOREIGN KEY (id) REFERENCES public.usuario(id);
 M   ALTER TABLE ONLY public.gerente DROP CONSTRAINT fkfrnjv0h1r1ce51i1gjgj8p4a9;
       public       postgres    false    2785    206    219            ?
           2606    19539 '   funcionario fkjsvlnmymcpdtn32hfainpxb7f    FK CONSTRAINT     ?   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT fkjsvlnmymcpdtn32hfainpxb7f FOREIGN KEY (id) REFERENCES public.usuario(id);
 Q   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT fkjsvlnmymcpdtn32hfainpxb7f;
       public       postgres    false    205    2785    219            ?
           2606    19529 %   acrescimos fkphyw1k5mwvf009ikii320ny6    FK CONSTRAINT     ?   ALTER TABLE ONLY public.acrescimos
    ADD CONSTRAINT fkphyw1k5mwvf009ikii320ny6 FOREIGN KEY (itenspedido_id) REFERENCES public.itens_pedido(id);
 O   ALTER TABLE ONLY public.acrescimos DROP CONSTRAINT fkphyw1k5mwvf009ikii320ny6;
       public       postgres    false    2775    210    196            ?
           2606    19584 1   produtos_ingredientes fkshjb47qvgqcv7rju0a9lv1w2f    FK CONSTRAINT     ?   ALTER TABLE ONLY public.produtos_ingredientes
    ADD CONSTRAINT fkshjb47qvgqcv7rju0a9lv1w2f FOREIGN KEY (ingrediente_id) REFERENCES public.ingrediente(id);
 [   ALTER TABLE ONLY public.produtos_ingredientes DROP CONSTRAINT fkshjb47qvgqcv7rju0a9lv1w2f;
       public       postgres    false    208    2773    217            j      x?????? ? ?      l      x?????? ? ?      n      x?????? ? ?      p      x?????? ? ?      r      x?????? ? ?      s      x?????? ? ?      t      x?????? ? ?      v      x?????? ? ?      x      x?????? ? ?      z      x?????? ? ?      |      x?????? ? ?      ~      x?????? ? ?            x?????? ? ?      ?      x?????? ? ?     
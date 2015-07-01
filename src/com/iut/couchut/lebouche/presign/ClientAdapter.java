package com.iut.couchut.lebouche.presign;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Valentin on 20/06/2015.
 */
public class ClientAdapter extends BaseAdapter {

    private List<Client> LesClients;
    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return LesClients.size();
    }

    @Override
    public Object getItem(int arg0) {
        return LesClients.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;


        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.vue_client, null);
            holder.vid = (TextView)convertView.findViewById(R.id.idClient);
            holder.vnom = (TextView)convertView.findViewById(R.id.nomClient);
            holder.vprenom = (TextView)convertView.findViewById(R.id.prenomClient);
            holder.vtl = (TextView)convertView.findViewById(R.id.telClient);
            holder.vad = (TextView)convertView.findViewById(R.id.addrClient);
            holder.vcp = (TextView)convertView.findViewById(R.id.cpClient);
            holder.vville = (TextView)convertView.findViewById(R.id.villeClient);


            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.vid.setText(LesClients.get(position).getIdCli());
        holder.vnom.setText(LesClients.get(position).getNomCli());
        holder.vprenom.setText(LesClients.get(position).getPrenomCli());
        holder.vtl.setText(LesClients.get(position).getTel());
        holder.vad.setText(LesClients.get(position).getAdresse());
        holder.vcp.setText(LesClients.get(position).getCp());
        holder.vville.setText(LesClients.get(position).getVille());

        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.rgb(238, 233, 233));
        }
        else {
            convertView.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        return convertView;
    }

    public List<Client> getLesClients() {
        return LesClients;
    }

    public ClientAdapter (List<Client> lClient, Context context){
        LesClients = lClient;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView vid, vnom, vprenom, vtl, vad, vcp,  vville;
    }
}
